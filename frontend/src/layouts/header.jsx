import { useTheme } from "@/hooks/use-theme";
import { Bell, ChevronsLeft, Moon, Sun, LogOut, User } from "lucide-react";
import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios"; // Import axios

export const Header = ({ collapsed, setCollapsed }) => {
    const { theme, setTheme } = useTheme();
    const navigate = useNavigate();
    const [currentTime, setCurrentTime] = useState(new Date());
    const [showProfileDropdown, setShowProfileDropdown] = useState(false);
    const [showPasswordPopup, setShowPasswordPopup] = useState(false);
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const userData = {
        role: localStorage.getItem("userRole"),
        username: localStorage.getItem("staffName"),
        id: localStorage.getItem("userId")
    };

    const API_BASE_URL = import.meta.env.VITE_API_URL;

    const handleLogout = () => {
        localStorage.removeItem("userRole");
        localStorage.removeItem("staffName");
        localStorage.removeItem("userId");
        navigate("/");
    };

    const handlePasswordChange = async () => {
        if (newPassword !== confirmPassword) {
            setError("Passwords don't match");
            return;
        }
        if (newPassword.length < 6) {
            setError("Password must be at least 6 characters");
            return;
        }

        try {
           

         
            const response = await axios.post(`${API_BASE_URL}/register`, {
                id: Number(userData.id),
                role: userData.role,
                password: newPassword,
                username: userData.username,
                name: localStorage.getItem("staffName"),
            });
 


            if (response.status === 200) {
                setSuccess("Password changed successfully!");
                setError("");
                setNewPassword("");
                setConfirmPassword("");
                setTimeout(() => {
                    setShowPasswordPopup(false);
                    setSuccess("");
                }, 2000);
            } else {
                setError(response.data.message || "Failed to change password");
            }
        } catch (err) {
            setError(err.response?.data?.message || "An error occurred. Please try again.");
        }
    };

    // Update time every second
    useEffect(() => {
        const timer = setInterval(() => {
            setCurrentTime(new Date());
        }, 1000);

        return () => clearInterval(timer);
    }, []);

    // Format time as HH:MM:SS AM/PM
    const formatTime = (date) => {
        return date.toLocaleTimeString('en-US', {
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
            hour12: true
        });
    };

    return (
        <header className="relative z-10 flex h-[60px] items-center justify-between bg-white px-4 shadow-md transition-colors dark:bg-slate-900">
            <div className="flex items-center gap-x-3">
                <button
                    className="btn-ghost size-10"
                    onClick={() => setCollapsed(!collapsed)}
                >
                    <ChevronsLeft className={collapsed && "rotate-180"} />
                </button>
            </div>
            <div className="flex items-center gap-x-3">
                <div className="hidden sm:block text-sm font-medium dark:text-white">
                    {formatTime(currentTime)}
                </div>
                <button
                    className="btn-ghost size-10"
                    onClick={() => setTheme(theme === "light" ? "dark" : "light")}
                >
                    <Sun
                        size={20}
                        className="dark:hidden"
                    />
                    <Moon
                        size={20}
                        className="hidden dark:block"
                    />
                </button>
                
                {/* Profile Dropdown */}
                <div className="relative">
                    <button
                        className="flex items-center gap-2 rounded-full p-1 hover:bg-slate-100 dark:hover:bg-slate-800"
                        onClick={() => setShowProfileDropdown(!showProfileDropdown)}
                    >
                        <div className="size-8 rounded-full bg-blue-100 dark:bg-blue-900 flex items-center justify-center">
                            <User size={16} className="text-blue-600 dark:text-blue-300" />
                        </div>
                        <span className="hidden sm:inline text-sm font-medium dark:text-white">
                            {userData.username}
                        </span>
                    </button>
                    
                    {showProfileDropdown && (
                        <div className="absolute right-0 mt-2 w-48 bg-white dark:bg-slate-800 rounded-md shadow-lg py-1 z-50">
                            <div className="px-4 py-2 text-sm text-gray-700 dark:text-gray-300 border-b dark:border-slate-700">
                                {userData.username}
                                <div className="text-xs text-gray-500 dark:text-gray-400">
                                    {userData.role}
                                </div>
                            </div>
                            {userData.role === 'admin' && (
                                <button
                                    className="block w-full text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-slate-700"
                                    onClick={() => {
                                        setShowPasswordPopup(true);
                                        setShowProfileDropdown(false);
                                    }}
                                >
                                    Change Password
                                </button>
                            )}
                            <button
                                className=" w-full flex text-left px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-slate-700"
                                onClick={handleLogout}
                            >
                              <LogOut size={18}/> Logout
                            </button>
                        </div>
                    )}
                </div>
            </div>

            {/* Password Change Popup */}
            {showPasswordPopup && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
                    <div className="bg-white dark:bg-slate-800 p-6 rounded-lg shadow-lg w-full max-w-md">
                        <h3 className="text-lg font-semibold mb-4 dark:text-white">Change Password</h3>
                        
                        {error && <div className="text-red-500 mb-4">{error}</div>}
                        {success && <div className="text-green-500 mb-4">{success}</div>}
                        
                        <div className="space-y-4">
                            <div>
                                <label className="block text-sm font-medium mb-1 dark:text-gray-300">
                                    New Password
                                </label>
                                <input
                                    type="password"
                                    className="w-full px-3 py-2 border rounded-md dark:bg-slate-700 dark:border-slate-600 dark:text-white"
                                    value={newPassword}
                                    onChange={(e) => setNewPassword(e.target.value)}
                                />
                            </div>
                            <div>
                                <label className="block text-sm font-medium mb-1 dark:text-gray-300">
                                    Confirm Password
                                </label>
                                <input
                                    type="password"
                                    className="w-full px-3 py-2 border rounded-md dark:bg-slate-700 dark:border-slate-600 dark:text-white"
                                    value={confirmPassword}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                />
                            </div>
                        </div>
                        
                        <div className="mt-6 flex justify-end space-x-3">
                            <button
                                className="px-4 py-2 text-sm font-medium rounded-md bg-gray-200 dark:text-white hover:bg-gray-300 dark:bg-slate-700 dark:hover:bg-slate-600"
                                onClick={() => {
                                    setShowPasswordPopup(false);
                                    setError("");
                                    setSuccess("");
                                }}
                            >
                                Cancel
                            </button>
                            <button
                                className="px-4 py-2 text-sm font-medium rounded-md bg-blue-600 text-white hover:bg-blue-700"
                                onClick={handlePasswordChange}
                            >
                                Change Password
                            </button>
                        </div>
                    </div>
                </div>
            )}
        </header>
    );
};

Header.propTypes = {
    collapsed: PropTypes.bool,
    setCollapsed: PropTypes.func,
};