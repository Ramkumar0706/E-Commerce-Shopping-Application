import React, { useState } from 'react';
import axios from 'axios';

const AddImage = ({ product }) => {
  const [imageType, setImageType] = useState('');
  const [imageFile, setImageFile] = useState(null);
  const [message, setMessage] = useState('');

  const handleImageTypeChange = (e) => {
    setImageType(e.target.value);
  };

  const handleImageFileChange = (e) => {
    setImageFile(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('image', imageFile);
    formData.append('productId',1)
    formData.append('imageType',imageType)
  
    try {
      const response = await axios.post(`http://localhost:8080/api/ecav1/products/{productId}/image-type/{imageType}/image`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },withCredentials: true
      });
  
      if (response.status === 200) {
        console.log("ram success");
      }
      // setMessage(response.data.message);
    } catch (error) {
      if (error.response && error.response.data) {
        setMessage(error.response.data.message);
      } else {
        setMessage('An error occurred. Please try again later.');
        console.error('Error:', error);
      }
    }
  };
  
  return (
    <div className="max-w-md mx-auto">
      <h2 className="text-2xl font-bold mb-4">Upload Product Image</h2>
      <form onSubmit={handleSubmit} className="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <div className="mb-4">
          <label htmlFor="imageType" className="block text-gray-700 font-bold mb-2">
            Image Type:
          </label>
          <select
            id="imageType"
            value={imageType}
            onChange={handleImageTypeChange}
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          >
            <option value="">Select Image Type</option>
            <option value="COVER">Cover</option>
            <option value="NORMAL">Normal</option>
          </select>
        </div>
        <div className="mb-6">
          <label htmlFor="imageFile" className="block text-gray-700 font-bold mb-2">
            Image File:
          </label>
          <input
            type="file"
            id="imageFile"
            onChange={handleImageFileChange}
            className="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
          />
        </div>
        <div className="flex items-center justify-between">
          <button
            type="submit"
            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
          >
            Upload
          </button>
        </div>
      </form>
      {message && <p className="text-green-500">{message}</p>}
    </div>
  );
};

export default AddImage;