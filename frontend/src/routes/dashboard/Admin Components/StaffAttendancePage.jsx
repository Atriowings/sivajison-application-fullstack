import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getPresentStaff, getAbsentStaff, markAbsent } from "../../Api/Attendance";
import { attendanceService } from "../../Api/attendanceService";

const StaffattendancePage = ({ staffempid, setStaffEmpid }) => {
  const navigate = useNavigate();
  const userName = localStorage.getItem('staffName');
  const empId = localStorage.getItem('userEmpid');
  
  // State management
  const [fullName, setFullName] = useState(userName);
  const [employeeId, setEmployeeId] = useState(empId);
  const [currentDateTime, setCurrentDateTime] = useState("");
 
  const [attendanceRegistered, setAttendanceRegistered] = useState(false);
 
  const [staff, setStaff] = useState([]);
  const [astaff, setAstaff] = useState([]);
  const [loading, setLoading] = useState({
    present: true,
    absent: true
  });
  const [error, setError] = useState({
    present: "",
    absent: ""
  });

  // Format time to 12-hour AM/PM format
  const formatTime = (timeString) => {
    if (!timeString) return "Not Recorded";
  
    try {
      // Create a Date object using today's date with the UTC time
      const [hours, minutes] = timeString.split(':');
      const date = new Date();
      date.setHours(parseInt(hours, 10), parseInt(minutes, 10), 0, 0);
  
      // Convert to IST using toLocaleTimeString with Asia/Kolkata timeZone
      const localTime = date.toLocaleTimeString('en-IN', {
        timeZone: 'Asia/Kolkata',
        hour: '2-digit',
        minute: '2-digit',
        hour12: true
      });
  
      return localTime;
    } catch (err) {
      console.error("Error formatting time:", err);
      return timeString;
    }
  };

  const fetchPresentStaff = async () => {
    try {
      setLoading(prev => ({ ...prev, present: true }));
      setError(prev => ({ ...prev, present: "" }));
      
      const data = await getPresentStaff();
      // Filter out admins
      const nonAdminStaff = data.filter(s => s.role !== "admin");
      setStaff(nonAdminStaff);
    } catch (error) {
      console.error("Error:", error);
      setError(prev => ({ ...prev, present: "Failed to load present staff data" }));
    } finally {
      setLoading(prev => ({ ...prev, present: false }));
    }
  };

  const fetchAbsentStaff = async () => {
    try {
      setLoading(prev => ({ ...prev, absent: true }));
      setError(prev => ({ ...prev, absent: "" }));
      
      const data = await getAbsentStaff();
      // Filter out admins
      const nonAdminAstaff = data.filter(s => s.role !== "admin");
      setAstaff(nonAdminAstaff);
    } catch (error) {
      console.error("Error:", error);
      setError(prev => ({ ...prev, absent: "Failed to load absent staff data" }));
    } finally {
      setLoading(prev => ({ ...prev, absent: false }));
    }
  };

  const handleMarkAbsent = async (staffId) => {
    try {
      await markAbsent(staffId);
      // Refresh both tables after marking absent
      await fetchPresentStaff();
      await fetchAbsentStaff();
    } catch (error) {
      console.error("Error marking staff as absent:", error);
      alert("Failed to mark staff as absent");
    }
  };

  const handleSubmit = async (empid, staffname) => {
    try {
      const attendanceData = {
        empid,
        staffname,
      };
  
      await attendanceService.registerAttendance(attendanceData);
  
      setAttendanceRegistered(true);
      await fetchPresentStaff();
      await fetchAbsentStaff();
    } catch (error) {
      console.error("Error submitting attendance:", error);
      alert("Failed to mark as present");
    }
  };
  

  useEffect(() => {
    fetchPresentStaff();
    fetchAbsentStaff();
  }, []);

  const handleViewDetails = (staffId) => {
    navigate(`/admin/monthlyattendance/${staffId}`);
  };

  return (
    <div className="min-h-screen p-6 max-w-6xl mx-auto space-y-6">
      {/* Present Staff Table */}
      <div className="dark:bg-gray-900 dark:text-white bg-gray-50 p-6 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">Today's Present List</h1>
        </div>
        
        {loading.present ? (
          <div className="p-4 text-center">Loading present staff...</div>
        ) : error.present ? (
          <div className="p-4 bg-red-100 text-red-700 rounded-lg">
            {error.present}
          </div>
        ) : (
          <div className="overflow-x-auto rounded-lg shadow-sm">
            <table className="w-full">
              <thead className="dark:bg-gray-800 bg-gray-100">
                <tr>
                  <th className="p-4 text-left">S.No</th>
                  <th className="p-4 text-left">Staff ID</th>
                  <th className="p-4 text-left">Staff Name</th>
                  <th className="p-4 text-left">Status</th>
                  <th className="p-4 text-left">In Time</th>
                  <th className="p-4 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {staff.length > 0 ? (
                  staff.map((s, index) => (
                    <tr 
                      key={s.id} 
                      className="border-b border-gray-200 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-800 transition-colors"
                    >
                      <td className="p-4">{index + 1}</td>
                      <td className="p-4">{s.empid}</td>
                      <td className="p-4">{s.staffname}</td>
                      <td className="p-4">
                        <span className="px-3 py-1 rounded-full text-sm font-semibold bg-green-700 text-white">
                          Present
                        </span>
                      </td>
                      <td className="p-4">
                        {s.intime ? formatTime(s.intime) : "N/A"}
                      </td>
                      <td className="p-4 space-x-2">
                        <button 
                          onClick={() => handleViewDetails(s.empid)}
                          className="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700 transition-colors"
                        >
                          View Monthly
                        </button>
                        <button 
                          onClick={() => handleMarkAbsent(s.id)}
                          className="bg-red-600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition-colors"
                        >
                          Mark Absent
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="6" className="p-4 text-center text-gray-500">
                      No present staff records found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* Absent Staff Table */}
      <div className="dark:bg-gray-900 dark:text-white bg-gray-50 p-6 rounded-lg shadow-lg">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-2xl font-bold">Today's Absent List</h1>
        </div>
        
        {loading.absent ? (
          <div className="p-4 text-center">Loading absent staff...</div>
        ) : error.absent ? (
          <div className="p-4 bg-red-100 text-red-700 rounded-lg">
            {error.absent}
          </div>
        ) : (
          <div className="overflow-x-auto rounded-lg shadow-sm">
            <table className="w-full">
              <thead className="dark:bg-gray-800 bg-gray-100">
                <tr>
                  <th className="p-4 text-left">S.No</th>
                  <th className="p-4 text-left">Staff ID</th>
                  <th className="p-4 text-left">Staff Name</th>
                  <th className="p-4 text-left">Status</th>
                  <th className="p-4 text-left">Monthly Attendance</th>
                  <th className="p-4 text-left">Actions</th>
                </tr>
              </thead>
              <tbody>
                {astaff.length > 0 ? (
                  astaff.map((s, index) => (
                    <tr 
                      key={s.id} 
                      className="border-b border-gray-200 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-800 transition-colors"
                    >
                      <td className="p-4">{index + 1}</td>
                      <td className="p-4">{s.empid}</td>
                      <td className="p-4">{s.username}</td>
                      <td className="p-4">
                        <span className="px-3 py-1 rounded-full text-sm font-semibold bg-red-700 text-white">
                          Absent
                        </span>
                      </td>
                      <td className="p-4">
                        <button 
                          onClick={() => handleViewDetails(s.empid)}
                          className="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700 transition-colors"
                        >
                          View Monthly
                        </button>
                      </td>
                      <td className="p-4">
                        <button 
                          onClick={() => handleSubmit(s.empid, s.username)}
                          className="bg-green-600 text-white px-4 py-2 rounded-lg hover:bg-green-700 transition-colors"
                        >
                         Mark As Present
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="5" className="p-4 text-center text-gray-500">
                      No absent staff records found
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default StaffattendancePage;