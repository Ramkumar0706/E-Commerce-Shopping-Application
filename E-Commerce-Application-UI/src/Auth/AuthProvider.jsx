import React,{createContext,useContext,useState} from 'react'

export const authContext=createContext({})

const AuthProvider = ({children}) => {
 
    const [user,setUser]=useState({
        userId:"",
        username:"",
        authenticated:false,
        role:"CUSTOMER",
        accessExpriration:0,
        RefershExpiration:0
    })
    console.log(user)
  return (
    <authContext.Provider value={{user,setUser}}>{children}</authContext.Provider>
    
  )
}

export default AuthProvider

//custom hook
export const useAuth=()=>useContext(authContext);