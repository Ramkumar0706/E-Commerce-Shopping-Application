import React, { useState, useEffect } from 'react';
import axios from 'axios';
import SearchFilter from './SearchFilter';

const Home = () => {
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);

  useEffect(() => {
    const fetchProducts = async () => {
      const response = await axios.get('http://localhost:8080/api/ecav1/findProduct', {
        headers: {
          'Content-Type': 'application/json'
        },
        withCredentials: true
      });

      console.log(response.data);
      setProducts(response.data.data);
      setFilteredProducts(response.data.data);
    };

    fetchProducts();
  }, []);

  const handleFilter = (filters) => {
    const filtered = products.filter((product) => {
      let isMatch = true;

      if (filters.minPrice && Number(product.
        productPrice
        ) < filters.minPrice) {
        isMatch = false;
      }

      if (filters.maxPrice && Number(product.productPrice) > filters.maxPrice) {
        isMatch = false;
      }

      if (filters.category && product.category !== filters.category) {
        isMatch = false;
      }

      if (filters.availability && product.availability !== filters.availability) {
        isMatch = false;
      }

      return isMatch;
    });

    setFilteredProducts(filtered);
  };

  const toggleSearchFilter = () => {
    setShowSearchFilter(!showSearchFilter);
  };

  const [showSearchFilter, setShowSearchFilter] = useState(false);

  return (
    <div className="container mx-auto px-4 py-8 mt-16">
      <h1 className="text-3xl font-bold mb-4">Products</h1>

      <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded " onClick={toggleSearchFilter}>
        Filter
      </button>

      {showSearchFilter && <div className='absolute w-full  translate-x-5'> <SearchFilter  onFilter={handleFilter} filters={{ minPrice: 0, maxPrice: 0, category: '', availability: '' }} /></div>}

      <div  className=" grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {filteredProducts.map((product) => (
          <div key={product.Id} className="bg-white shadow-md rounded p-4">
             <img width={350} height={150} src={`http://localhost:8080/api/ecav1/image/${product.coverImage}`} alt={product.productBrand} />
           <div className="flex justify-between flex-wrap p-2 ">
             {product.normalImage.map((image)=>(
              <img  className='hover:cursor-pointer w-100 h-50' 
              width={100} height={50} src={`http://localhost:8080/api/ecav1/image/${image}`} 
              alt={product.productBrand} />
             ))}
             </div>
            <h2 className="text-xl font-bold mb-2">{product.productBrand}</h2>
            <p className="text-gray-600 mb-2">{product.productModel}</p>
            <p className="text-gray-600 mb-2">Price: ${product.productPrice}</p>
            <p className="text-gray-600 mb-2">Description: {product.productDescription}</p>
            <p className="text-gray-600 mb-2">Category: {product.category}</p>
            <p className="text-gray-600">Availability: {product.availability}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;