import axios from 'axios';
import React, { useState } from 'react';
import { IoSearchOutline } from 'react-icons/io5';

const SearchText = ({ onSearch }) => {
  const [search, setSearch] = useState('');

  const handleSubmit = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/ecav1/products?serachText=${search}`, {
        headers: {
          'Content-Type': 'application/json'
        },
        withCredentials: true
      });

      // Pass the search results to the parent component
      onSearch(response.data.data);
    } catch (error) {
      if (error.response) {
        console.error('Server error:', error.response.data, error.response.status);
      } else if (error.request) {
        console.error('Request failed, could not reach server.');
      } else {
        console.error('Error creating request:', error.message);
      }
    }
  };

  return (
    <div className=' w-full flex items-center justify-end '>
      <div className="text-2xl text-slate-500">
        <IoSearchOutline />
      </div>
      <input
        type="text"
        placeholder="Search the Product Here!..."
        onChange={(e) => setSearch(e.target.value)}
        className="border-0 rounded-xl bg-blue-50 placeholder:text-slate-500 hover:placeholder:text-slate-400 h-full outline-none px-2 py-2 w-full text-gray-700"
      />
      <button className='bg-cyan-400  py-1  px-2 mr-1 hover:bg-slate-600 ' onClick={handleSubmit}>
        Search
      </button>
    </div>
  );
};

export default SearchText;
