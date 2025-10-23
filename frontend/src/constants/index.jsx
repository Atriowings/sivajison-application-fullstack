import { 
  CalendarCheck,
  ClipboardList,
  Bell,
  ClipboardCheck,
  UserCog,
  Plane,
  Hotel,
  FolderArchive,
  FileText,
  Users,
  ClipboardEdit,
  Mail,
  Settings,
  FileArchive
} from "lucide-react";

const staffName = localStorage.getItem("staffName")

export const navbarLinks = {
  staff: [
    {
      title: staffName,
      links: [
        { 
          label: "Attendance Page", 
          icon: CalendarCheck, 
          path: "/staff/attendance" 
        },
        {
          label: "Data Entry Page", 
          icon: ClipboardList, 
          path: "/staff/dataentry", 
          sublinks: [
            { label: "Flights", icon: Plane, path: "/staff/dataentry/flights" },
            { label: "Hotels", icon: Hotel, path: "/staff/dataentry/hotels" },
            { label: "Others", icon: Hotel, path: "/staff/dataentry/other" },
          ],
        },
        { 
          label: "Pending Works", 
          icon: ClipboardCheck, 
          path: "/staff/pendingwork" 
        },
        { 
          label: "Notice Board", 
          icon: ClipboardList, 
          path: "/staff/reminder" 
        },
        {
          label: "Management",
          icon: UserCog,
          path: "#",
          sublinks: [
            { label: "Flight Customers", icon: Plane, path: "/staff/flightcustomers" },
            { label: "Hotel Customers", icon: Hotel, path: "/staff/hotelcustomers" },
            { label: "Other", icon: Users, path: "/staff/othercustomers" },
          ],
        },
        {
          label: "AddingDetails",
          icon: Settings,
          path: "/staff/dropdowndatas",
        },
        {
          label: "Resources",
          icon: FileArchive,
          path: "/staff/resources",
        },
        {
          label: "Daily Report",
          icon: FileText,
          path: "/staff/dailyreport",
        },
        {
          label: "Custom Mails",
          icon: Mail,
          path: "/staff/custommail",
        },
        {
          label: "Customer Details",
          icon: FileArchive,
          path: "/staff/customerdetails",
        },
        {
          label: "Upcoming Bookings",
          icon: ClipboardList,
          path: "/staff/remindercustomer",
        },
      ],
    },
  ],
  admin: [
    {
      title: "Admin Panel",
      links: [
        {
          label: "Staff attendance",
          icon: CalendarCheck,
          path: "/admin/staffattendance",
        },
        {
          label: "Manage Staff",
          icon: Users,
          path: "/admin/managestaff",
        },
        {
          label: "Assign Task",
          icon: ClipboardEdit,
          path: "/admin/assigntask",
        },
        {
          label: "Data Entry Page", 
          icon: ClipboardList, 
          path: "/admin/dataentry", 
          sublinks: [
            { label: "Flights", icon: Plane, path: "/admin/dataentry/flights" },
            { label: "Hotels", icon: Hotel, path: "/admin/dataentry/hotels" },
            { label: "Others", icon: Hotel, path: "/admin/dataentry/other" },
          ],
        },
        { 
          label: "Notice Board", 
          icon: ClipboardList, 
          path: "/admin/reminder" 
        },
       
        {
          label: "Management",
          icon: UserCog,
          path: "#",
          sublinks: [
            { label: "Flight Customers", icon: Plane, path: "/admin/flightcustomers" },
            { label: "Hotel Customers", icon: Hotel, path: "/admin/hotelcustomers" },
            { label: "Others", icon: Users, path: "/admin/othercustomers" },
          ],
        },
        {
          label: "Admin Resources",
          icon: FileArchive,
          path: "/admin/resources",
        },
        {
          label: "AddingDetails",
          icon: Settings,
          path: "/admin/dropdowndatas",
        },
        {
          label: "Custom Mails",
          icon: Mail,
          path: "/admin/custommail",
        },
        {
          label: "Customer Details",
          icon: FileArchive,
          path: "/admin/customerdetails",
        },
        {
          label: "Upcoming Bookings",
          icon: ClipboardList,
          path: "/admin/remindercustomer",
        },
      ],
    },
  ],
};