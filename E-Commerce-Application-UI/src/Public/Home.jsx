// Home.js
import React, { useState, useEffect, useContext } from 'react';
import axios from 'axios';
import SearchFilter from './SearchFilter';
import SearchText from '../Public/SearchText'; 
import { SearchContext } from './Context/SearchContext';
import { useNavigate } from 'react-router-dom';



const Home = () => {
  const searchContext = useContext(SearchContext);

  

  const { searchResults } = searchContext;
  
  console.log(searchResults)
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [orderBy, setOrderBy] = useState("ASC");
  const [sortBy, setSortBy] = useState("category");
  const [searchFilter, setSearchFilter] = useState({
    minPrice: 2000,
    maxPrice: 0,
    category: null,
    availability: null
  });
  const{minPrice,maxPrice,category,availability}=searchFilter
  


useEffect(() => {
  const fetchProducts = async () => {
    console.log('ram')
    const response = await axios.get(`http://localhost:8080/api/ecav1/findProduct?page=${page}&orderBy=${orderBy}&sortBy=${sortBy}`, {
       // Pass formData as params
       params:searchFilter,
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
}, [searchFilter.category, page, orderBy, sortBy]);


  const handleFilter = (filters) => {
    setSearchFilter(filters)
  };

  const toggleSearchFilter = () => {
    setShowSearchFilter(!showSearchFilter);
  };

  const [showSearchFilter, setShowSearchFilter] = useState(false);
  const navigate = useNavigate();
const handleProductDetail =(e)=>{
  navigate("/productDetail",e.target.value)
}
  
 
  return (
    <div className="container mx-auto px-4 py-8 mt-16">
      <h1 className="text-3xl font-bold mb-4">Products</h1>

      <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded " onClick={toggleSearchFilter}>
        Filter
      </button>

      {showSearchFilter && <div className='absolute w-full  translate-x-5'> 
      <SearchFilter onFilter={handleFilter} filters={searchFilter} /></div>}
   
      <div className=" grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-4">
        {(searchResults.length > 0 ? searchResults : filteredProducts).map((product) => (
          <div key={product.Id} className="bg-white shadow-md rounded p-4  hover:border 4px border-orange-300  cursor-pointer" onClick={
            handleProductDetail
          }>
            <img width={350} height={150} src={`http://localhost:8080/api/ecav1/image/${product.coverImage}`} alt={product.productBrand} />
            {/* <div className="flex justify-between flex-wrap p-2 ">
              {product.normalImage.map((image) => (
                <img className='hover:cursor-pointer w-100 h-50'
                  width={100} height={50} src={`http://localhost:8080/api/ecav1/image/${image}`}
                  alt={product.productBrand} />
              ))}
            </div> */}
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
