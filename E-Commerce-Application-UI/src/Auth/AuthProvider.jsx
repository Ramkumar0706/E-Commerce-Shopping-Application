import React,{createContext,useContext,useState,useEffect} from 'react'
import RefreshAuth from './RefreshAuth';

export const authContext=createContext({})

const AuthProvider = ({children}) => {
  const {auth}=RefreshAuth();

    const [user,setUser]=useState({
        userId:"",
        username:"",
        authenticated:false,
        role:"CUSTOMER",
        accessExpriration:0,
        RefershExpiration:0
    })
    console.log(user)
    useEffect(()=>{
      setUser(auth)
    },[auth])

  return (
    <authContext.Provider value={{user,setUser}}>{children}</authContext.Provider>
    
  )
}

export default AuthProvider

//custom hook
export const useAuth=()=>useContext(authContext);