import React from 'react'
import Header from './Util/Header'
import {Outlet, Router} from 'react-router-dom'

import { SearchProvider } from './Public/Context/SearchContext'


const App = () => {
  return (
    <SearchProvider>
    <div>
      <Header />
      <div>
        <Outlet />
      </div>
    </div>
  </SearchProvider>
  )
}

export default App