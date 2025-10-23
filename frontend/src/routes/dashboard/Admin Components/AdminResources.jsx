import { useState, useEffect, useRef, useCallback } from "react";
import { FileText, Download, Trash2, Search, Upload, X, Link2, Type } from "lucide-react";
import { 
  getFiles, uploadFile, removeFile, downloadFile,
  addResource, getResources , deleteResource
} from "../../Api/Resources";

// UI Components
const Button = ({ children, className, ...props }) => (
  <button 
    className={`px-4 py-2 rounded-md transition-colors duration-200 flex items-center gap-2 ${className}`} 
    {...props}
  >
    {children}
  </button>
);

const Input = ({ className, ...props }) => (
  <input 
    className={`border rounded-md p-2 bg-white dark:bg-gray-800 dark:border-gray-700 ${className}`} 
    {...props} 
  />
);

const Card = ({ children, className }) => (
  <div className={`border rounded-lg bg-white dark:bg-gray-800 dark:border-gray-700 shadow-sm ${className}`}>
    {children}
  </div>
);

const Modal = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow-xl max-w-md w-full max-h-[90vh] overflow-y-auto">
        <div className="flex justify-end p-2">
          <button 
            onClick={onClose}
            className="text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-200"
          >
            <X className="h-6 w-6" />
          </button>
        </div>
        <div className="p-6">
          {children}
        </div>
      </div>
    </div>
  );
};

const AdminResources = () => {
  // File management states
  const [files, setFiles] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState("");
  const [error, setError] = useState(null);
  const [uploadModalOpen, setUploadModalOpen] = useState(false);
  const [uploading, setUploading] = useState(false);
  const [selectedFile, setSelectedFile] = useState(null);
  const [isDragging, setIsDragging] = useState(false);
  const [downloading, setDownloading] = useState(false);
  const fileInputRef = useRef(null);
  const userRole = localStorage.getItem('userRole');
  // Resource management states
  const [resources, setResources] = useState([]);
  const [resourceModalOpen, setResourceModalOpen] = useState(false);
  const [newResource, setNewResource] = useState({
    link: "",
    words: ""
  });
  const [resourceLoading, setResourceLoading] = useState(false);

  // Fetch both files and resources
  const fetchData = async () => {
    try {
      setLoading(true);
      setResourceLoading(true);
      
      // Fetch files
      const filesData = await getFiles();
      const formattedFiles = filesData.map(file => ({
        ...file,
        name: file.name || "Untitled",
        url: file.url || "#"
      }));
      setFiles(formattedFiles);
      
      // Fetch resources
      const resourcesData = await getResources();
      setResources(resourcesData);
      
      setError(null);
    } catch (err) {
      console.error("Error fetching data:", err);
      setError("Failed to load data. Please try again.");
    } finally {
      setLoading(false);
      setResourceLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  // Filter files and resources based on search query
  const filteredFiles = files.filter(file => {
    const fileName = file.filename || "";
    return fileName.toLowerCase().includes(searchQuery.toLowerCase());
  });

  const filteredResources = resources.filter(resource => {
    const resourceWords = resource.words || "";
    const resourceLink = resource.link || "";
    return (
      resourceWords.toLowerCase().includes(searchQuery.toLowerCase()) ||
      resourceLink.toLowerCase().includes(searchQuery.toLowerCase())
    );
  });

  // File operations
  const handleDelete = async (fileId) => {
    try {
      await removeFile(fileId);
      await fetchData();
    } catch (err) {
      console.error("Error deleting file:", err);
      setError("Failed to delete file. Please try again.");
    }
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setSelectedFile(file);
    }
  };

  const handleDragEnter = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsDragging(true);
  }, []);

  const handleDragLeave = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsDragging(false);
  }, []);

  const handleDragOver = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
  }, []);

  const handleDrop = useCallback((e) => {
    e.preventDefault();
    e.stopPropagation();
    setIsDragging(false);
    
    const files = e.dataTransfer.files;
    if (files && files.length > 0) {
      setSelectedFile(files[0]);
    }
  }, []);

  const handleUpload = async () => {
    if (!selectedFile) {
      setError("Please select a file to upload");
      return;
    }

    try {
      setUploading(true);
      const formData = new FormData();
      formData.append("file", selectedFile);

      await uploadFile(formData);
      await fetchData();
      
      setUploadModalOpen(false);
      setSelectedFile(null);
      if (fileInputRef.current) {
        fileInputRef.current.value = "";
      }
    } catch (err) {
      console.error("Error uploading file:", err);
      setError("Failed to upload file. Please try again.");
    } finally {
      setUploading(false);
    }
  };

  const handleDownload = async (fileId, fileName) => {
    try {
      setDownloading(true);
      await downloadFile(fileId, fileName);
    } catch (err) {
      console.error("Error downloading file:", err);
      setError("Failed to download file. Please try again.");
    } finally {
      setDownloading(false);
    }
  };

  // Resource operations
  const handleAddResource = async () => {
    // Validate at least one field is filled
    if (!newResource.link.trim() && !newResource.words.trim()) {
      setError("Please fill in at least one field (link or words)");
      return;
    }

    // If link is provided, validate URL format
    if (newResource.link.trim()) {
      try {
        new URL(newResource.link); // This will throw if invalid URL
      } catch (e) {
        setError("Please enter a valid URL (e.g., https://example.com)");
        return;
      }
    }

    try {
      setResourceLoading(true);
      setError(null); // Clear any previous errors
      await addResource(newResource);
      await fetchData();
      
      setResourceModalOpen(false);
      setNewResource({ link: "", words: "" });
    } catch (err) {
      console.error("Error adding resource:", err);
      setError("Failed to add resource. Please try again.");
    } finally {
      setResourceLoading(false);
    }
  };
  const handleDeleteResource = async (resourceId) => {
    try {
      await deleteResource(resourceId);
      await fetchData();
    } catch (err) {
      console.error("Error deleting resource:", err);
      setError("Failed to delete resource. Please try again.");
    }
  };

  return (
    <div className="container mx-auto px-4 py-8 dark:bg-gray-900 max-w-7xl">
      {error && (
        <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded">
          {error}
        </div>
      )}

      <div className="flex flex-col md:flex-row md:items-center md:justify-between mb-8 gap-4">
        <div>
          <h1 className="text-2xl font-bold text-gray-800 dark:text-white">Resources</h1>
          <p className="text-sm text-gray-600 dark:text-gray-400">
            Manage all files and links for your website
          </p>
        </div>

        {userRole === 'admin' && (   <div className="flex gap-3">
          <Button 
            className="bg-blue-600 hover:bg-blue-700 text-white"
            onClick={() => setResourceModalOpen(true)}
          >
            <Link2 className="h-4 w-4" />
            Add Link & Words
          </Button>
          <Button 
            className="bg-green-600 hover:bg-green-700 text-white"
            onClick={() => setUploadModalOpen(true)}
          >
            <Upload className="h-4 w-4" />
            Upload File
          </Button>
        </div>)}
      </div>

      <div className="mb-6">
        <div className="relative">
          <Search className="absolute left-3 top-3 h-4 w-4 text-gray-400 dark:text-gray-500" />
          <Input
            type="text"
            placeholder="Search files and links..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            className="pl-9 w-full"
          />
        </div>
      </div>

      {/* Resources (Links & Words) Section */}
      <div className="mb-8">
        <h2 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">Links & Words</h2>
        <Card className="mb-6">
          <div className="overflow-x-auto">
            {resourceLoading ? (
              <div className="flex justify-center items-center h-32">
                <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-gray-900 dark:border-gray-100"></div>
              </div>
            ) : (
              <table className="w-full">
                <thead className="bg-gray-50 dark:bg-gray-700">
                  <tr>
                    <th className="text-left p-4 text-gray-600 dark:text-gray-300 font-medium text-sm">
                      Link
                    </th>
                    <th className="text-left p-4 text-gray-600 dark:text-gray-300 font-medium text-sm">
                      Words
                    </th>
                    {userRole === 'admin' && (     <th className="text-left p-4 text-gray-600 dark:text-gray-300 font-medium text-sm">
                      Actions
                    </th>)}
                  </tr>
                </thead>
                <tbody>
                  {filteredResources.length > 0 ? (
                    filteredResources.map((resource) => (
                      <tr 
                        key={resource.id} 
                        className="border-b dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-700/50"
                      >
                        <td className="p-4">
                          <a 
                            href={resource.link} 
                            target="_blank" 
                            rel="noopener noreferrer"
                            className="text-blue-600 dark:text-blue-400 hover:underline break-all"
                          >
                            {resource.link}
                          </a>
                        </td>
                        <td className="p-4 text-gray-800 dark:text-gray-200">
                          {resource.words}
                        </td>
                        {userRole === 'admin' && (   <td className="p-4">
                          <button
                            onClick={() => handleDeleteResource(resource.id)}
                            className="text-red-600 hover:text-red-800 dark:text-red-400 dark:hover:text-red-300"
                            title="Delete"
                          >
                            <Trash2 className="h-5 w-5" />
                          </button>
                        </td>)}
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="3" className="p-4 text-center text-gray-500 dark:text-gray-400">
                        {searchQuery ? "No matching resources found" : "No resources available"}
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            )}
          </div>
        </Card>
      </div>

      {/* Files Section */}
      <div>
        <h2 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">Files</h2>
        <Card>
          <div className="overflow-x-auto">
            {loading ? (
              <div className="flex justify-center items-center h-32">
                <div className="animate-spin rounded-full h-8 w-8 border-t-2 border-b-2 border-gray-900 dark:border-gray-100"></div>
              </div>
            ) : (
              <table className="w-full">
                <thead className="bg-gray-50 dark:bg-gray-700">
                  <tr>
                    <th className="text-left p-4 text-gray-600 dark:text-gray-300 font-medium text-sm">
                      File Name
                    </th>
                    <th className="text-left p-4 text-gray-600 dark:text-gray-300 font-medium text-sm">
                      Actions
                    </th>
                  </tr>
                </thead>
                <tbody>
                  {filteredFiles.length > 0 ? (
                    filteredFiles.map((file) => (
                      <tr 
                        key={file.id} 
                        className="border-b dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-700/50"
                      >
                        <td className="p-4 text-gray-800 dark:text-gray-200">
                          <div className="flex items-center gap-2">
                            <FileText className="h-5 w-5 text-gray-500" />
                            <span className="truncate max-w-xs">{file.filename}</span>
                          </div>
                        </td>
                        <td className="p-4">
                          <div className="flex gap-4">
                            <button
                              onClick={() => handleDownload(file.id, file.filename)}
                              disabled={downloading}
                              className={`text-green-600 hover:text-green-800 dark:text-green-400 dark:hover:text-green-300 ${
                                downloading ? 'opacity-50 cursor-not-allowed' : ''
                              }`}
                              title="Download"
                            >
                              {downloading ? (
                                <span className="inline-flex items-center">
                                  <svg className="animate-spin -ml-1 mr-1 h-4 w-4" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                                    <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                                    <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                                </svg>
                                </span>
                              ) : (
                                <Download className="h-5 w-5" />
                              )}
                            </button>
                            <button
                              onClick={() => handleDelete(file.id)}
                              className="text-red-600 hover:text-red-800 dark:text-red-400 dark:hover:text-red-300"
                              title="Delete"
                            >
                              <Trash2 className="h-5 w-5" />
                            </button>
                          </div>
                        </td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="2" className="p-4 text-center text-gray-500 dark:text-gray-400">
                        {searchQuery ? "No matching files found" : "No files available"}
                      </td>
                    </tr>
                  )}
                </tbody>
              </table>
            )}
          </div>
        </Card>
      </div>

      {/* Add Resource Modal */}
      <Modal isOpen={resourceModalOpen} onClose={() => {
    setResourceModalOpen(false);
    setNewResource({ link: "", words: "" });
    setError(null); // Clear error when closing modal
  }}>
    <h2 className="text-xl font-bold mb-4 text-gray-800 dark:text-white">Add New Resource</h2>
    
    {error && (
      <div className="mb-4 p-3 bg-red-100 border border-red-400 text-red-700 rounded text-sm">
        {error}
      </div>
    )}
    
    <div className="space-y-4">
      <div>
        <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
          Link URL
        </label>
        <Input
          type="text"
          placeholder="https://example.com"
          value={newResource.link}
          onChange={(e) => {
            setNewResource({...newResource, link: e.target.value});
            setError(null); // Clear error when typing
          }}
          className="w-full text-black dark:text-white"
        />
      </div>
      
      <div>
        <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
          Associated Words
        </label>
        <Input
          type="text"
          placeholder="Enter words"
          value={newResource.words}
          onChange={(e) => {
            setNewResource({...newResource, words: e.target.value});
            setError(null); // Clear error when typing
          }}
          className="w-full text-black dark:text-white"
        />
      </div>
    </div>

    <div className="flex justify-end gap-2 pt-6">
      <Button
        className="bg-gray-200 hover:bg-gray-300 text-gray-800 dark:bg-gray-700 dark:hover:bg-gray-600 dark:text-white"
        onClick={() => {
          setResourceModalOpen(false);
          setNewResource({ link: "", words: "" });
          setError(null);
        }}
      >
        Cancel
      </Button>
      <Button
        className={`${
          !newResource.link.trim() && !newResource.words.trim()
            ? "bg-blue-400 cursor-not-allowed"
            : "bg-blue-600 hover:bg-blue-700"
        } text-white`}
        onClick={handleAddResource}
        disabled={resourceLoading || (!newResource.link.trim() && !newResource.words.trim())}
      >
        {resourceLoading ? "Adding..." : "Add Resource"}
      </Button>
    </div>
  </Modal>
      {/* Upload File Modal */}
      <Modal isOpen={uploadModalOpen} onClose={() => {
        setUploadModalOpen(false);
        setSelectedFile(null);
        setIsDragging(false);
        if (fileInputRef.current) {
          fileInputRef.current.value = "";
        }
      }}>
        <h2 className="text-xl font-bold mb-4 text-gray-800 dark:text-white">Upload File</h2>
        
        <div 
          className={`border-2 border-dashed rounded-lg p-8 text-center transition-colors ${
            isDragging 
              ? 'border-green-500 bg-green-50 dark:bg-green-900/20' 
              : 'border-gray-300 dark:border-gray-600 hover:border-green-400 dark:hover:border-green-400'
          }`}
          onDragEnter={handleDragEnter}
          onDragLeave={handleDragLeave}
          onDragOver={handleDragOver}
          onDrop={handleDrop}
          onClick={() => fileInputRef.current?.click()}
        >
          <Upload className="mx-auto h-12 w-12 text-gray-400 dark:text-gray-500 mb-3" />
          <p className="text-sm text-gray-600 dark:text-gray-400 mb-1">
            {isDragging ? 'Drop your file here' : 'Drag and drop your file here or click to browse'}
          </p>
          <p className="text-xs text-gray-500 dark:text-gray-500">
            Supported formats: Any file type
          </p>
          <input
            type="file"
            ref={fileInputRef}
            onChange={handleFileChange}
            className="hidden"
          />
        </div>

        {selectedFile && (
          <div className="mt-4 p-3 bg-gray-50 dark:bg-gray-700 rounded-md flex items-center justify-between">
            <div className="flex items-center gap-2 overflow-hidden">
              <FileText className="h-5 w-5 text-gray-500 dark:text-gray-400 flex-shrink-0" />
              <div className="truncate">
                <p className="text-sm font-medium text-gray-800 dark:text-gray-200 truncate">
                  {selectedFile.name}
                </p>
                <p className="text-xs text-gray-500 dark:text-gray-400">
                  {(selectedFile.size / 1024).toFixed(2)} KB
                </p>
              </div>
            </div>
            <button
              onClick={() => {
                setSelectedFile(null);
                if (fileInputRef.current) {
                  fileInputRef.current.value = "";
                }
              }}
              className="text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-200"
            >
              <X className="h-4 w-4" />
            </button>
          </div>
        )}

        <div className="flex justify-end gap-2 pt-6">
          <Button
            className="bg-gray-200 hover:bg-gray-300 text-gray-800 dark:bg-gray-700 dark:hover:bg-gray-600 dark:text-white"
            onClick={() => {
              setUploadModalOpen(false);
              setSelectedFile(null);
              setIsDragging(false);
              if (fileInputRef.current) {
                fileInputRef.current.value = "";
              }
            }}
          >
            Cancel
          </Button>
          <Button
            className="bg-green-600 hover:bg-green-700 text-white"
            onClick={handleUpload}
            disabled={!selectedFile || uploading}
          >
            {uploading ? "Uploading..." : "Upload File"}
          </Button>
        </div>
      </Modal>
    </div>
  );
};

export default AdminResources;