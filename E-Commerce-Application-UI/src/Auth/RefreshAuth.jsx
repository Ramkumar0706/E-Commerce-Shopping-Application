import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Navigate, useNavigate } from 'react-router-dom';

const RefreshAuth = () =>{
    const[auth,setAuth]=useState({
         userId:"",
        username:"",
        authenticated:false,
        role:"CUSTOMER",
        accessExpriration:0,
        RefershExpiration:0
    })
    

    
    const doRefresh = async () =>{
           
    }

    const handleRefersh = () =>{
        const authResponse=localStorage.getItem("authResponse");
        if(authResponse!=null)
        {
            const user = JSON.parse(authResponse);
            const accessExpiration=new Date(user.accessExpiration);
            const refreshExpiration=new Date(user.refreshExpiration);
        if(refreshExpiration>new Date())
        {
            if(accessExpiration>new Date())
            {
                setAuth(user);
            }else doRefresh();
        }
        }
    }
    
    let refreshing=false;
    useEffect(() =>{
        if(!refreshing)
        {
            refreshing=true;
            handleRefersh();
        }
    },[]);

    return {auth};
}
export default RefreshAuth;