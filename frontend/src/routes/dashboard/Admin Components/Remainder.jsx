import React, { useState, useEffect, useRef } from "react";
import axios from "axios";

const RemindersPage = () => {
  // State for form fields
  const [formData, setFormData] = useState({
    id: "",
    visaappointment: "",
    updates: "",
    paymentdues: "",
    date: ""
  });

  const API_BASE_URL = import.meta.env.VITE_API_URL;
  const [reminders, setReminders] = useState([]);
  const [showConfirmation, setShowConfirmation] = useState(false);
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
  const [reminderToDelete, setReminderToDelete] = useState(null);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [userRole, setUserRole] = useState("");
  const formRef = useRef(null);
  const dateInputRef = useRef(null);

  // Format date to "DD-MM-YYYY"
  const formatDate = (dateString) => {
    if (!dateString) return "";
    const date = new Date(dateString);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const year = date.getFullYear();
    return `${day}-${month}-${year}`;
  };

  // Fetch user role and reminders on component mount
  useEffect(() => {
    const role = localStorage.getItem("userRole");
    setUserRole(role);
    fetchReminders();
  }, []);

  // Fetch reminders from API and sort by date (newest first)
  const fetchReminders = async () => {
    try {
      const response = await axios.get(`${API_BASE_URL}/admin/GetReminder`);
    
      const sortedReminders = [...response.data].sort((a, b) => {
        return new Date(a.finaldate) - new Date(b.finaldate);
      });
      // below two line codes are used to fetch data without any filters default values
      // setReminders(sortedReminders);
      //  const sortedReminders = response.data;
       setReminders(sortedReminders);
    } catch (error) {
      console.error("Error fetching reminders:", error);
    }
  };

  // Check if user is staff
  const isStaff = userRole === "staff";

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  // Check if form has at least one field filled
  const hasAtLeastOneField = () => {
    return (
      formData.visaappointment.trim() !== "" ||
      formData.updates.trim() !== "" ||
      formData.paymentdues.trim() !== "" ||
      formData.date.trim() !== ""
    );
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (!hasAtLeastOneField()) {
      alert("Please fill in at least one field before submitting.");
      return;
    }
    
    setIsSubmitting(true);
    setShowConfirmation(true);
  };

  // Handle confirmation
  const handleConfirmation = async (confirmed) => {
    setShowConfirmation(false);
  
    if (confirmed) {
      setIsSubmitting(true);
  
      try {
        // Prepare the data for API
        const reminderData = {
          visaappointment: formData.visaappointment,
          updates: formData.updates,
          paymentdues: formData.paymentdues,
          finaldate: formData.date
        };

        // If editing, include the ID (only allowed for non-staff)
        if (isEditing && !isStaff) {
          reminderData.id = formData.id;
        }

        // Send data to the API
        await axios.post(
          `${API_BASE_URL}/admin/AddReminder`,
          reminderData,
          {
            headers: {
              "Content-Type": "application/json",
            }
          }
        );

        // Refresh the reminders list
        await fetchReminders();

        // Reset form
        resetForm();

      } catch (error) {
        console.error("Error submitting form:", error);
        if (error.response) {
          console.error("Backend response error:", error.response.data);
          alert(`Error: ${error.response.data.message || "Failed to submit data"}`);
        } else {
          alert("Failed to connect to server. Please try again.");
        }
      } finally {
        setIsSubmitting(false);
      }
    } else {
      setIsSubmitting(false);
    }
  };

  // Reset form
  const resetForm = () => {
    setFormData({
      id: "",
      visaappointment: "",
      updates: "",
      paymentdues: "",
      date: ""
    });
    setIsEditing(false);
  };

  // Edit a reminder - Disabled for staff
  const editReminder = (reminder) => {
    if (isStaff) return;
    
    setFormData({
      id: reminder.id,
      visaappointment: reminder.visaappointment,
      updates: reminder.updates,
      paymentdues: reminder.paymentdues,
      date: reminder.finaldate
    });
    setIsEditing(true);
    
    // Scroll to the form
    if (formRef.current) {
      formRef.current.scrollIntoView({ behavior: 'smooth' });
    }
  };

  // Handle delete reminder - Disabled for staff
  const handleDeleteReminder = (reminderId) => {
    if (isStaff) return;
    
    setReminderToDelete(reminderId);
    setShowDeleteConfirmation(true);
  };

  // Confirm delete reminder
  const confirmDeleteReminder = async (confirmed) => {
    setShowDeleteConfirmation(false);
    
    if (confirmed && reminderToDelete && !isStaff) {
      try {
        await axios.post(`${API_BASE_URL}/admin/DeleteReminder/${reminderToDelete}`);
        // Refresh the reminders list
        await fetchReminders();
      } catch (error) {
        console.error("Error deleting reminder:", error);
        alert("Failed to delete reminder. Please try again.");
      }
    }
    
    setReminderToDelete(null);
  };

  // Handle clicking on date input field to show picker
  const handleDateInputClick = () => {
    if (dateInputRef.current) {
      dateInputRef.current.showPicker();
    }
  };

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900 py-10 px-4">
      <div className="max-w-7xl gap-5 mx-auto md:grid md:grid-cols-2">
        {/* Form Card - Show for all users (including staff) */}
        <div className="bg-white h-min dark:bg-gray-800 p-8 rounded-xl shadow-md border border-gray-200 dark:border-gray-700 mb-8" ref={formRef}>
          <div className="mb-8 text-center">
            <h2 className="text-3xl font-bold text-green-600">
              {isEditing && !isStaff ? "Edit Reminder" : "Add New Reminder"}
            </h2>
            <p className="mt-2 text-gray-600 dark:text-gray-400">
              Fill in the details (at least one field is required)
            </p>
          </div>

          <form onSubmit={handleSubmit} className="grid grid-cols-1 gap-6">
            {/* Visa Appointment */}
            <div className="space-y-1">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">
                Visa Appointment
              </label>
              <input
                type="text"
                name="visaappointment"
                value={formData.visaappointment}
                onChange={handleChange}
                className="w-full px-4 py-3 rounded-lg border border-gray-300 dark:border-gray-600 focus:border-green-600 focus:ring-green-200 dark:focus:ring-green-800 focus:ring-2 focus:outline-none transition-all bg-white dark:bg-gray-700 text-gray-900 dark:text-white"
                placeholder="Enter visa appointment details"
              />
            </div>

            {/* Updates */}
            <div className="space-y-1">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">
                Updates
              </label>
              <textarea
                name="updates"
                value={formData.updates}
                onChange={handleChange}
                rows="3"
                className="w-full px-4 py-3 rounded-lg border border-gray-300 dark:border-gray-600 focus:border-green-600 focus:ring-green-200 dark:focus:ring-green-800 focus:ring-2 focus:outline-none transition-all bg-white dark:bg-gray-700 text-gray-900 dark:text-white"
                placeholder="Enter updates"
              />
            </div>

            {/* Payment Dues */}
            <div className="space-y-1">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">
                Payment Dues
              </label>
              <input
                type="text"
                name="paymentdues"
                value={formData.paymentdues}
                onChange={handleChange}
                className="w-full px-4 py-3 rounded-lg border border-gray-300 dark:border-gray-600 focus:border-green-600 focus:ring-green-200 dark:focus:ring-green-800 focus:ring-2 focus:outline-none transition-all bg-white dark:bg-gray-700 text-gray-900 dark:text-white"
                placeholder="Enter payment dues details"
              />
            </div>

            {/* Date */}
            <div className="space-y-1">
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300">
                Date
              </label>
              <input
                type="date"
                name="date"
                ref={dateInputRef}
                value={formData.date}
                onChange={handleChange}
                onClick={handleDateInputClick}
                className="w-full md:w-1/3 px-4 py-3 rounded-lg border border-gray-300 dark:border-gray-600 focus:border-green-600 focus:ring-green-200 dark:focus:ring-green-800 focus:ring-2 focus:outline-none transition-all bg-white dark:bg-gray-700 text-gray-900 dark:text-white cursor-pointer"
                placeholder="Select date"
              />
            </div>

            {/* Submit/Cancel Buttons */}
            <div className="flex justify-end gap-4 mt-4">
              {isEditing && !isStaff && (
                <button
                  type="button"
                  onClick={resetForm}
                  className="px-6 py-3 rounded-lg font-semibold border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition"
                >
                  Cancel
                </button>
              )}
              <button
                type="submit"
                disabled={isSubmitting || !hasAtLeastOneField()}
                className={`px-6 py-3 rounded-lg font-semibold text-white transition-all duration-300 flex items-center justify-center ${
                  isSubmitting
                    ? "bg-green-400 cursor-not-allowed"
                    : !hasAtLeastOneField()
                    ? "bg-gray-400 cursor-not-allowed"
                    : "bg-green-600 hover:bg-green-700 shadow-md hover:shadow-lg"
                } min-w-[150px]`}
              >
                {isSubmitting ? (
                  <>
                    <svg className="animate-spin -ml-1 mr-2 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                      <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                      <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                    </svg>
                    Processing...
                  </>
                ) : (
                  isEditing && !isStaff ? "Update" : "Submit"
                )}
              </button>
            </div>
          </form>

          {/* Confirmation Popup */}
          {showConfirmation && (
            <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
              <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-lg max-w-md w-full mx-4 border border-gray-200 dark:border-gray-700">
                <div className="flex items-center mb-4">
                  <div className="bg-green-100 dark:bg-green-900 p-2 rounded-full mr-3">
                    <svg className="w-6 h-6 text-green-600 dark:text-green-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                    </svg>
                  </div>
                  <h3 className="text-lg font-semibold text-gray-800 dark:text-white">Confirm</h3>
                </div>
                <p className="text-gray-600 dark:text-gray-300 mb-6">
                  {isEditing && !isStaff
                    ? "Are you sure you want to update this reminder?" 
                    : "Are you sure you want to add this new reminder?"}
                </p>
                <div className="flex justify-end gap-3">
                  <button
                    onClick={() => handleConfirmation(false)}
                    className="px-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition"
                  >
                    Cancel
                  </button>
                  <button
                    onClick={() => handleConfirmation(true)}
                    className="px-4 py-2 rounded-lg bg-green-600 hover:bg-green-700 text-white transition flex items-center"
                  >
                    <svg className="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 13l4 4L19 7"></path>
                    </svg>
                    Confirm
                  </button>
                </div>
              </div>
            </div>
          )}
        </div>

        {/* Reminders List - Notepad Style */}
        <div className="bg-white dark:bg-gray-800 p-8 rounded-xl shadow-md border border-gray-200 dark:border-gray-700">
          <h3 className="text-2xl font-bold text-green-600 mb-6">Your Reminders</h3>
          
          {reminders.length === 0 ? (
            <p className="text-gray-600 dark:text-gray-400 text-center py-8">
              No reminders found. Add a new reminder to get started.
            </p>
          ) : (
            <div className="space-y-6">
              {reminders.map((reminder) => (
                <div 
                  key={reminder.id} 
                  className="p-6 bg-yellow-50 dark:bg-gray-700 border-l-4 border-green-500 rounded-lg shadow-sm relative"
                >
                  {/* Reminder content */}
                  <div className="space-y-3">
                    {reminder.visaappointment && (
                      <div>
                        <h4 className="font-semibold text-gray-800 dark:text-white">Visa Appointment:</h4>
                        <p className="text-gray-700 dark:text-gray-300 pl-4">{reminder.visaappointment}</p>
                      </div>
                    )}
                    
                    {reminder.updates && (
                      <div>
                        <h4 className="font-semibold text-gray-800 dark:text-white">Updates:</h4>
                        <p className="text-gray-700 dark:text-gray-300 whitespace-pre-line pl-4">{reminder.updates}</p>
                      </div>
                    )}
                    
                    {reminder.paymentdues && (
                      <div>
                        <h4 className="font-semibold text-grey-800 dark:text-white">Payment Dues:</h4>
                        <p className="text-red-800 dark:text-red-500 font-bold pl-4">{reminder.paymentdues}</p>
                      </div>
                    )}

                    {reminder.finaldate && (
                      <div>
                        <h4 className="font-semibold text-grey-800 dark:text-white">Date:</h4>
                        <p className="text-gray-700 dark:text-gray-300 whitespace-pre-line pl-4">
                          {formatDate(reminder.finaldate)}
                        </p>
                      </div>
                    )}
                  </div>
                  
                  {/* Action buttons - Only show edit/delete if not staff */}
                  {!isStaff && (
                    <div className="absolute top-4 right-4 flex space-x-2">
                      <button
                        onClick={() => editReminder(reminder)}
                        className="text-green-600 hover:text-green-800 dark:hover:text-green-400 p-1 rounded-full hover:bg-green-100 dark:hover:bg-gray-600"
                        title="Edit"
                      >
                        <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                      </button>
                      <button
                        onClick={() => handleDeleteReminder(reminder.id)}
                        className="text-red-600 hover:text-red-800 dark:hover:text-red-400 p-1 rounded-full hover:bg-red-100 dark:hover:bg-gray-600"
                        title="Delete"
                      >
                        <svg className="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                        </svg>
                      </button>
                    </div>
                  )}
                </div>
              ))}
            </div>
          )}
        </div>

        {/* Delete Confirmation Popup */}
        {showDeleteConfirmation && (
          <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
            <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-lg max-w-md w-full mx-4 border border-gray-200 dark:border-gray-700">
              <div className="flex items-center mb-4">
                <div className="bg-red-100 dark:bg-red-900 p-2 rounded-full mr-3">
                  <svg className="w-6 h-6 text-red-600 dark:text-red-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                  </svg>
                </div>
                <h3 className="text-lg font-semibold text-gray-800 dark:text-white">Delete Reminder</h3>
              </div>
              <p className="text-gray-600 dark:text-gray-300 mb-6">
                Are you sure you want to delete this reminder? This action cannot be undone.
              </p>
              <div className="flex justify-end gap-3">
                <button
                  onClick={() => confirmDeleteReminder(false)}
                  className="px-4 py-2 rounded-lg border border-gray-300 dark:border-gray-600 text-gray-700 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-700 transition"
                >
                  Cancel
                </button>
                <button
                  onClick={() => confirmDeleteReminder(true)}
                  className="px-4 py-2 rounded-lg bg-red-600 hover:bg-red-700 text-white transition flex items-center"
                >
                  <svg className="w-4 h-4 mr-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"></path>
                  </svg>
                  Delete
                </button>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default RemindersPage;