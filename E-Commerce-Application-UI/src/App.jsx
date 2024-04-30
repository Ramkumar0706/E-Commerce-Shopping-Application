import React from 'react'
import Header from './Util/Header'
import {Outlet} from 'react-router-dom'
import MyProfile from './Private/Common/MyProfile'

const App = () => {
  return (
  <div>
    <Header
    />
    
    <div>
    <Outlet/> 
    </div>
    </div>
  )
}

export default App