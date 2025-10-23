// src/components/CustomerDetailsPage.jsx
import React, { useState, useEffect } from "react";
import { FaTrash, FaSearch, FaSyncAlt } from "react-icons/fa";
import axios from "axios";

const CustomerDetails = () => {
  // State management
  const [customers, setCustomers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
  const [customerToDelete, setCustomerToDelete] = useState(null);
  const API_BASE_URL = import.meta.env.VITE_API_URL;
  // Fetch data on component mount
  useEffect(() => {
    fetchCustomerData();
  }, []);

  const fetchCustomerData = async () => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await axios.get( `${API_BASE_URL}/admin/GetCustomers`);
      
      // Transform data to match our expected structure
      const transformedData = response.data.map((customer, index) => ({
        id: index + 1,
        name: customer.customername ,
        email: customer.customermail ,
        phone: customer.customernumber,
        // Add any additional fields from the API response if needed
      }));
      
      setCustomers(transformedData);
    } catch (err) {
      setError(err.message || "Failed to fetch customer data");
    } finally {
      setLoading(false);
    }
  };

  // Handle deletion of customers
  const handleDeleteClick = (customerId) => {
    setCustomerToDelete(customerId);
    setShowDeleteConfirmation(true);
  };

  const confirmDelete = (confirmed) => {
    if (confirmed && customerToDelete !== null) {
      setCustomers(prev => prev.filter(c => c.id !== customerToDelete));
    }
    setShowDeleteConfirmation(false);
    setCustomerToDelete(null);
  };

  // Filter customers based on search term
  const filteredCustomers = customers.filter(customer =>
    customer.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    customer.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
    customer.phone.toLowerCase().includes(searchTerm.toLowerCase())
  );

  // Loading state
  if (loading) {
    return (
      <div className="min-h-screen p-6 max-w-6xl mx-auto flex flex-col items-center justify-center">
        <div className="animate-pulse flex flex-col items-center">
          <div className="w-16 h-16 bg-blue-500 rounded-full mb-4 flex items-center justify-center">
            <FaSyncAlt className="text-white text-2xl animate-spin" />
          </div>
          <div className="text-2xl font-medium text-gray-700 dark:text-gray-300 mb-2">
            Loading Customer Data
          </div>
          <div className="text-gray-500 dark:text-gray-400">
            Please wait while we fetch customer details...
          </div>
        </div>
      </div>
    );
  }

  // Error state
  if (error) {
    return (
      <div className="min-h-screen p-6 max-w-6xl mx-auto flex flex-col items-center justify-center">
        <div className="text-center">
          <div className="w-16 h-16 bg-red-500 rounded-full mb-4 flex items-center justify-center mx-auto">
            <svg className="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
          </div>
          <h2 className="text-2xl font-bold text-red-500 mb-2">Error Loading Data</h2>
          <p className="text-gray-600 dark:text-gray-300 mb-6">{error}</p>
          <button 
            onClick={fetchCustomerData}
            className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded-md transition-colors flex items-center gap-2 mx-auto"
          >
            <FaSyncAlt /> Try Again
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen p-4 md:p-6 max-w-6xl mx-auto">
      <div className="dark:bg-gray-900 dark:text-white bg-white p-4 md:p-6 rounded-xl shadow-md">
        <div className="flex flex-col md:flex-row md:items-center md:justify-between gap-4 mb-6">
          <div>
            <h2 className="text-2xl font-bold text-gray-900 dark:text-gray-100">
              Customer Details
            </h2>
           
          </div>
          
          <div className="relative w-full md:w-64">
            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <FaSearch className="text-gray-400" />
            </div>
            <input
              type="text"
              placeholder="Search customers..."
              className="pl-10 pr-4 py-2 w-full border border-gray-300 dark:border-gray-700 rounded-md bg-white dark:bg-gray-800 focus:outline-none focus:ring-2 focus:ring-blue-500"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
        </div>

        {/* Customer Details Table */}
        <div className="overflow-x-auto rounded-lg border border-gray-200 dark:border-gray-700">
          <table className="w-full">
            <thead className="dark:bg-gray-800 bg-gray-50">
              <tr>
                <th className="p-3 text-left text-sm font-semibold">S.No</th>
                <th className="p-3 text-left text-sm font-semibold">Name</th>
                <th className="p-3 text-left text-sm font-semibold">Email</th>
                <th className="p-3 text-left text-sm font-semibold">Phone Number</th>
                {/* <th className="p-3 text-left text-sm font-semibold">Actions</th> */}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200 dark:divide-gray-700">
              {filteredCustomers.length > 0 ? (
                filteredCustomers.map((customer, index) => (
                  <tr key={customer.id} className="hover:bg-gray-50 dark:hover:bg-gray-800/50">
                    <td className="p-3 text-sm whitespace-nowrap">{index + 1}</td>
                    <td className="p-3 font-medium text-sm whitespace-nowrap">
                      {customer.name}
                    </td>
                    <td className="p-3 text-sm whitespace-nowrap">
                      <a 
                        href={`mailto:${customer.email}`} 
                        className="text-blue-600 dark:text-blue-400 hover:underline"
                      >
                        {customer.email}
                      </a>
                    </td>
                    <td className="p-3 text-sm whitespace-nowrap">
                      <a 
                        href={`tel:${customer.phone}`} 
                        className="text-blue-600 dark:text-blue-400 hover:underline"
                      >
                        {customer.phone}
                      </a>
                    </td>
                    {/* <td className="p-3 whitespace-nowrap">
                      <button
                        onClick={() => handleDeleteClick(customer.id)}
                        className="text-red-500 hover:text-red-700 transition-colors p-1.5 rounded-full hover:bg-red-50 dark:hover:bg-red-900/20"
                        title="Delete"
                      >
                        <FaTrash className="w-4 h-4" />
                      </button>
                    </td> */}
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="5" className="p-6 text-center text-gray-500 dark:text-gray-400 bg-gray-50 dark:bg-gray-800/30">
                    {customers.length === 0 ? 'No customer data available' : 'No matching customers found'}
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>

        {/* Delete Confirmation Modal */}
        {showDeleteConfirmation && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 p-4 z-50">
            <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-xl w-full max-w-md">
              <div className="flex flex-col items-center text-center">
                <div className="w-16 h-16 bg-red-100 dark:bg-red-900/20 rounded-full flex items-center justify-center mb-4">
                  <FaTrash className="text-red-500 text-2xl" />
                </div>
                <h3 className="text-xl font-bold mb-2">Delete Customer Record</h3>
                <p className="mb-6 text-gray-600 dark:text-gray-300">
                  Are you sure you want to delete this customer record? This action cannot be undone.
                </p>
                <div className="flex justify-center space-x-4 w-full">
                  <button 
                    onClick={() => confirmDelete(false)} 
                    className="bg-gray-200 hover:bg-gray-300 dark:bg-gray-700 dark:hover:bg-gray-600 px-6 py-2 rounded-md transition-colors flex-1"
                  >
                    Cancel
                  </button>
                  <button 
                    onClick={() => confirmDelete(true)} 
                    className="bg-red-600 hover:bg-red-700 text-white px-6 py-2 rounded-md transition-colors flex-1"
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default CustomerDetails;