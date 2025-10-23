import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_URL;

export const addAirline = async (formData) => {
  const response = await axios.post(
    `${API_BASE_URL}/admin/NewAirline`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    }
  );


  return response.data;
};


// export const editAirline = async (formData) => {
//   const response = await axios.post(
//     `${API_BASE_URL}/admin/AddAirline`,
//     formData,
//     {
//       headers: {
//         'Content-Type': 'multipart/form-data',
//       },
//     }
//   );
//   return response.data;
// };

export const addAirport = async (data) => {
  const response = await axios.post(
    `${API_BASE_URL}/admin/AddAirportCode`,
    data
  );
  return response.data;
};

export const getAirports = async () => {
  const response = await axios.get(
    `${API_BASE_URL}/staff/GetAirportCode`
  );
  return response;
};

export const getAirlines = async () => {
  const response = await axios.get(
    `${API_BASE_URL}/staff/GetAirline`
  );

 
  return response;
};

export const upAirline = async (formData) => {
  const response = await axios.post(
    `${API_BASE_URL}/admin/AddAirline`,
    formData,
    {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    }
  );


  return response.data;
};




