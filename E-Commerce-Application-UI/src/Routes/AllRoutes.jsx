import React from 'react'
import Explore from '../Private/Customer/Explore'
import WishList from '../Private/Customer/Wishlist'
import Cart from '../Private/Customer/Cart'
import VerifyOTP from '../Public/VerifyOTP'
import Home from '../Public/Home'
import Register from '../Public/Register'
import Login from '../Public/Login'
import AddAddress from '../Private/Common/AddAddress'
import EditProfile from '../Private/Common/EditProfile'
import {Route,Routes} from 'react-router-dom'
import App from '../App'
import SellerDashboard from '../Private/Seller/SellerDashboard'
import AddProduct from '../Private/Seller/AddPorduct'
import Orders from '../Private/Seller/Orders'


const AllRoutes = () => {
    

    const user={
        userId:"101",
        username:"Ramkumar",
        role:"CUSTOMER",
        authenticated:false,
        accessExpiration:3600,
        refershExpiration:1296000
    }
    const {role,authenticated}=user;
    let routes=[]
    
    if(authenticated){
        
        (role==="SELLER")?
        routes.push(
            <Route key={'1'} path='/seller-dashboard' element={<SellerDashboard/>}/>,
            <Route key={'2'} path='/add-product' element={<AddProduct/>}/>,
            <Route key={'21'} path='/orders' element={<Orders/>}/>
        ):(role==="CUSTOMER")&&
        routes.push(<Route key={'1'} path='/explore' element={<Explore/>}/>,
        <Route key={'3'}  path='/cart' element={<Cart/>}/>,
        <Route key={'4'}  path='/wishlist' element={<WishList/>}/>
        )
        routes.push(
            <Route key={'5'} path='/' element={<Home/>}/>,
            <Route key={'6'} path='/add-address' element={<AddAddress/>}/>,
            <Route key={'7'} path='/edit-profile' element={<EditProfile/>}/>,
        )
    }
        else{
            routes.push(
                <Route key={'8'} path='/' element={<Home/>}/>,
                <Route key={'9'} path='/login' element={<Login/>}/>,
                <Route key={'11'} path='/register' element={<Register/>}/>,
                <Route key={'12'} path='/verify-otp' element={<VerifyOTP/>}/>
            )
        }
  return <Routes> <Route path='/' element={<App/>}>{routes}</Route></Routes>
}

export default AllRoutes