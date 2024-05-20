import React, { createContext, useState } from 'react';

// Create a context for search results
const SearchContext = createContext();

// Create a provider component to provide search results to its children
const SearchProvider = ({ children }) => {
  const [searchResults, setSearchResults] = useState([]);
  
  return (
    <SearchContext.Provider value={{ searchResults, setSearchResults }}>
      {children}
    </SearchContext.Provider>
  );
};

export { SearchProvider, SearchContext };
