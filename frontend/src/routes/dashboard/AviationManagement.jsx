import { useState, useEffect } from 'react';
import { Plane, MapPin, Upload, Edit, Check, X } from 'lucide-react';
import { addAirline, addAirport, getAirports, getAirlines , upAirline } from '../Api/aviationApi';
const API_BASE_URL = import.meta.env.VITE_API_URL;
const AviationManagement = () => {
  const [activeTab, setActiveTab] = useState('airlines');
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [airports, setAirports] = useState([]);
  const [airlines, setAirlines] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [editValue, setEditValue] = useState('');
  const [editLogo, setEditLogo] = useState(null);

  // Form states
  const [newAirline, setNewAirline] = useState({ name: '', logo: null });
  const [newAirport, setNewAirport] = useState({ code: '' });

  const userRole = localStorage.getItem('userRole');

  // Fetch data when tab changes
  useEffect(() => {
    if (activeTab === 'airports') {
      fetchAirports();
    } else {
      fetchAirlines();
    }
  }, [activeTab]);

  const fetchAirports = async () => {
    setIsLoading(true);
    try {
      const response = await getAirports();
      setAirports(response.data);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to fetch airports');
    } finally {
      setIsLoading(false);
    }
  };

  const fetchAirlines = async () => {
    setIsLoading(true);
    try {
      const response = await getAirlines();
      setAirlines(response.data);
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to fetch airlines');
    } finally {
      setIsLoading(false);
    }
  };

  const handleAirlineSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);
    setSuccess(null);

    try {
      const formData = new FormData();
      formData.append('airline', newAirline.name);
      if (newAirline.logo) {
        formData.append('logo', newAirline.logo);
      }

      await addAirline(formData);
      setSuccess('Airline added successfully!');
      setNewAirline({ name: '', logo: null });
      fetchAirlines();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to add airline');
    } finally {
      setIsLoading(false);
    }
  };

  const handleAirportSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setError(null);
    setSuccess(null);

    try {
      await addAirport({ airportcode: newAirport.code });
      setSuccess('Airport added successfully!');
      setNewAirport({ code: '' });
      fetchAirports();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to add airport');
    } finally {
      setIsLoading(false);
    }
  };

  const handleEditStart = (item, type) => {
    setEditingId(item.id);
    if (type === 'airport') {
      setEditValue(item.airportcode);
    } else {
      setEditValue(item.airline);
      setEditLogo(null);
    }
  };

  const handleEditCancel = () => {
    setEditingId(null);
    setEditValue('');
    setEditLogo(null);
  };

  const handleAirportEditSave = async (id) => {
    setIsLoading(true);
    setError(null);
    setSuccess(null);
  
    try {
      // Make sure your addAirport function can handle updates when an ID is provided
      await addAirport({ airportcode: editValue, id });
      setSuccess('Airport updated successfully!');
      setEditingId(null);
      fetchAirports();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to update airport');
    } finally {
      setIsLoading(false);
    }
  };

  const handleAirlineEditSave = async (id) => {
    setIsLoading(true);
    setError(null);
    setSuccess(null);
  
    try {
      const formData = new FormData();
      formData.append('airline', editValue);
      formData.append('id', id); // ID in the body
       // console.log(response.data)(formData)
      if (editLogo) {
        formData.append('logo', editLogo);
      }
  
      await upAirline(formData);
      setSuccess('Airline updated successfully!');
      setEditingId(null);
      fetchAirlines();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to update airline');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="max-w-4xl mx-auto bg-white dark:bg-gray-800 rounded-xl shadow-md overflow-hidden">
      {/* Header with tabs */}
      <div className="flex border-b dark:border-gray-700">
        <button
          onClick={() => setActiveTab('airlines')}
          className={`flex-1 py-4 px-6 text-center font-medium text-sm flex items-center justify-center gap-2 transition-colors duration-200 ${
            activeTab === 'airlines'
              ? 'text-green-600 dark:text-green-400 border-b-2 border-green-500 bg-green-50 dark:bg-gray-700'
              : 'text-gray-500 dark:text-gray-400 hover:bg-gray-50 dark:hover:bg-gray-700'
          }`}
        >
          <Plane className="h-4 w-4" />
          Airlines
        </button>
        <button
          onClick={() => setActiveTab('airports')}
          className={`flex-1 py-4 px-6 text-center font-medium text-sm flex items-center justify-center gap-2 transition-colors duration-200 ${
            activeTab === 'airports'
              ? 'text-green-600 dark:text-green-400 border-b-2 border-green-500 bg-green-50 dark:bg-gray-700'
              : 'text-gray-500 dark:text-gray-400 hover:bg-gray-50 dark:hover:bg-gray-700'
          }`}
        >
          <MapPin className="h-4 w-4" />
          Airports
        </button>
      </div>

      {/* Content area */}
      <div className="p-6">
        {/* Status messages */}
        {error && (
          <div className="mb-4 p-3 text-sm bg-red-50 dark:bg-red-900/20 border border-red-200 dark:border-red-800 text-red-700 dark:text-red-300 rounded-lg flex items-start gap-2">
            <svg className="h-4 w-4 mt-0.5 flex-shrink-0" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            <span>{error}</span>
          </div>
        )}
        {success && (
          <div className="mb-4 p-3 text-sm bg-green-50 dark:bg-green-900/20 border border-green-200 dark:border-green-800 text-green-700 dark:text-green-300 rounded-lg flex items-start gap-2">
            <svg className="h-4 w-4 mt-0.5 flex-shrink-0" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
            </svg>
            <span>{success}</span>
          </div>
        )}

        {/* Airlines Form and Table */}
        {activeTab === 'airlines' && (
          <div className="space-y-6">
            <form onSubmit={handleAirlineSubmit} className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Airline Name</label>
                <input
                  type="text"
                  value={newAirline.name}
                  onChange={(e) => setNewAirline({...newAirline, name: e.target.value})}
                  className="w-full px-3 py-2 text-sm border border-gray-300 dark:border-gray-600 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-green-500 dark:bg-gray-700 dark:text-white"
                  placeholder="Enter airline name"
                  required
                />
              </div>

              <div>
                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Logo</label>
                <label className="flex items-center gap-2 text-sm border border-gray-300 dark:border-gray-600 rounded-md px-3 py-2 cursor-pointer hover:bg-gray-50 dark:hover:bg-gray-700 transition-colors duration-200">
                  <Upload size={14} className="text-gray-500 dark:text-gray-400" />
                  <span className="truncate">
                    {newAirline.logo ? newAirline.logo.name : 'Upload Logo'}
                  </span>
                  <input
                    type="file"
                    accept="image/*"
                    onChange={(e) => setNewAirline({...newAirline, logo: e.target.files[0]})}
                    className="hidden"
                    required
                  />
                </label>
                <p className="mt-1 text-xs text-gray-500 dark:text-gray-400">Recommended size: 200x200px (PNG or JPG)</p>
              </div>

              <div className="pt-2">
                <button
                  type="submit"
                  className="w-full sm:w-auto px-4 py-2 text-sm font-medium text-white bg-green-600 hover:bg-green-700 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 flex items-center justify-center gap-2 transition-colors duration-200"
                  disabled={isLoading}
                >
                  {isLoading ? (
                    <>
                      <svg className="animate-spin h-4 w-4" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                        <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                        <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                      </svg>
                      Adding Airline...
                    </>
                  ) : (
                    <>
                      <Plane className="h-4 w-4" />
                      Add Airline
                    </>
                  )}
                </button>
              </div>
            </form>

            {/* Airlines Table */}
            {userRole === 'admin' && (   <div className="mt-8">
              <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-4">Existing Airlines</h3>
              <div className="overflow-x-auto">
                <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                  <thead className="bg-gray-50 dark:bg-gray-700">
                    <tr>
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        S.No
                      </th>
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        Airline Name
                      </th>
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        Logo
                      </th> 
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        Actions
                      </th>
                    </tr>
                  </thead>
                  <tbody className="bg-white dark:bg-gray-800 divide-y divide-gray-200 dark:divide-gray-700">
                    {airlines.length > 0 ? (
                      airlines.map((airline, index) => (
                        <tr key={airline.id}>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-300">
                            {index + 1}
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 dark:text-white">
                            {editingId === airline.id ? (
                              <input
                                type="text"
                                value={editValue}
                                onChange={(e) => setEditValue(e.target.value)}
                                className="px-2 py-1 border border-gray-300 dark:border-gray-600 rounded-md dark:bg-gray-700 dark:text-white"
                              />
                            ) : (
                              airline.airline
                            )}
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap">
                            {editingId === airline.id ? (
                              <label className="flex items-center gap-2 text-sm cursor-pointer">
                                <Upload size={14} className="text-gray-500 dark:text-gray-400" />
                                <span className="truncate text-xs">
                                  {editLogo ? editLogo.name : 'Change Logo'}
                                </span>
                                <input
                                  type="file"
                                  accept="image/*"
                                  onChange={(e) => setEditLogo(e.target.files[0])}
                                  className="hidden"
                                />
                              </label>
                            ) : (
                              airline.logopath && (
                                <div className="h-10 w-20 flex items-center justify-center bg-gray-100 rounded">
                                  <img 
                                   src={`${API_BASE_URL}/logo/${encodeURIComponent(airline.airline)}`}
                                    alt={`${airline.airline} logo`} 
                                    className="h-full w-full object-contain"
                                    onError={(e) => {
                                      e.target.onerror = null; 
                                      e.target.src = 'path/to/default/logo.png';
                                    }}
                                  />
                                </div>
                              )
                            )}
                          </td> 
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-300">
                            {editingId === airline.id ? (
                              <div className="flex space-x-2">
                                <button
                                  onClick={() => handleAirlineEditSave(airline.id)}
                                  className="text-green-600 hover:text-green-900 dark:hover:text-green-400"
                                  disabled={isLoading}
                                >
                                  <Check size={16} />
                                </button>
                                <button
                                  onClick={handleEditCancel}
                                  className="text-red-600 hover:text-red-900 dark:hover:text-red-400"
                                >
                                  <X size={16} />
                                </button>
                              </div>
                            ) : (
                              <button
                                onClick={() => handleEditStart(airline, 'airline')}
                                className="text-blue-600 hover:text-blue-900 dark:hover:text-blue-400"
                              >
                                <Edit size={16} />
                              </button>
                            )}
                          </td>
                        </tr>
                      ))
                    ) : (
                      <tr>
                        <td colSpan="4" className="px-6 py-4 text-center text-sm text-gray-500 dark:text-gray-400">
                          {isLoading ? 'Loading airlines...' : 'No airlines found'}
                        </td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            </div>)}
          </div>
        )}

        {/* Airports Form and Table */}
        {activeTab === 'airports' && (
          <div className="space-y-6">
            <form onSubmit={handleAirportSubmit} className="space-y-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Airport Code (IATA)</label>
                <input
                  type="text"
                  value={newAirport.code}
                  onChange={(e) => setNewAirport({code: e.target.value})}
                  className="w-full px-3 py-2 text-sm border border-gray-300 dark:border-gray-600 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-green-500 dark:bg-gray-700 dark:text-white"
                  placeholder="e.g. Delhi (DEL)"
                  required
                  title="Please enter a valid 3-letter airport code"
                />
              </div>

              <div className="pt-2">
                <button
                  type="submit"
                  className="w-full sm:w-auto px-4 py-2 text-sm font-medium text-white bg-green-600 hover:bg-green-700 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 flex items-center justify-center gap-2 transition-colors duration-200"
                  disabled={isLoading}
                >
                  {isLoading ? (
                    <>
                      <svg className="animate-spin h-4 w-4" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                        <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                        <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                      </svg>
                      Adding Airport...
                    </>
                  ) : (
                    <>
                      <MapPin className="h-4 w-4" />
                      Add Airport
                    </>
                  )}
                </button>
              </div>
            </form>

            {/* Airports Table */}
            {userRole === 'admin' && (     <div className="mt-8">
              <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-4">Existing Airports</h3>
              <div className="overflow-x-auto">
                <table className="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
                  <thead className="bg-gray-50 dark:bg-gray-700">
                    <tr>
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        S.No
                      </th>
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        Airport Code
                      </th>
                      <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-gray-500 dark:text-gray-300 uppercase tracking-wider">
                        Actions
                      </th>
                    </tr>
                  </thead>
                  <tbody className="bg-white dark:bg-gray-800 divide-y divide-gray-200 dark:divide-gray-700">
                    {airports.length > 0 ? (
                      airports.map((airport, index) => (
                        <tr key={airport.id}>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-300">
                            {index + 1}
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900 dark:text-white">
                            {editingId === airport.id ? (
                              <input
                                type="text"
                                value={editValue}
                                onChange={(e) => setEditValue(e.target.value)}
                                className="px-2 py-1 border border-gray-300 dark:border-gray-600 rounded-md dark:bg-gray-700 dark:text-white"
                              />
                            ) : (
                              airport.airportcode
                            )}
                          </td>
                          <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500 dark:text-gray-300">
                            {editingId === airport.id ? (
                              <div className="flex space-x-2">
                                <button
                                  onClick={() => handleAirportEditSave(airport.id)}
                                  className="text-green-600 hover:text-green-900 dark:hover:text-green-400"
                                  disabled={isLoading}
                                >
                                  <Check size={16} />
                                </button>
                                <button
                                  onClick={handleEditCancel}
                                  className="text-red-600 hover:text-red-900 dark:hover:text-red-400"
                                >
                                  <X size={16} />
                                </button>
                              </div>
                            ) : (
                              <button
                                onClick={() => handleEditStart(airport, 'airport')}
                                className="text-blue-600 hover:text-blue-900 dark:hover:text-blue-400"
                              >
                                <Edit size={16} />
                              </button>
                            )}
                          </td>
                        </tr>
                      ))
                    ) : (
                      <tr>
                        <td colSpan="3" className="px-6 py-4 text-center text-sm text-gray-500 dark:text-gray-400">
                          {isLoading ? 'Loading airports...' : 'No airports found'}
                        </td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            </div>)}
          </div>
        )}
      </div>
    </div>
  );
};

export default AviationManagement;