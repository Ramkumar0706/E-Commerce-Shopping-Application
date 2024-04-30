import React, { useEffect, useState } from "react";
import axios from 'axios';

const Logout = ({ doAppear }) => {
  const [isSubmited, setIsSubmited] = useState(false);
  
  const [loginRequested, setLoginRequested] = useState(false)

  const handleLogout = async () => {
    try {

    const response = await  axios.post('http://localhost:8080/api/ecav1/user/logout',(''),{
      headers:{
        'Content-Type': 'application/json'
      },withCredentials:true})
console.log(response.status)
    
      if (response.status == 200) {
        localStorage.clear();
       window.location.reload();
      } 
    } catch (error) {
      console.log(error);
      setIsSubmited(false);
      alert(error?.response?.message);
    }
  };

  useEffect(() => {
    if (!loginRequested && isSubmited) {
      setLoginRequested(true);
      handleLogout();
    }
  }, [isSubmited]);
  return (
    <div
      className={`absolute w-max h-max top-18 right-20 px-6 hover:px-8 py-4 bg-white shadow-even20 shadow-gray-300 rounded-md flex flex-col justify-start items-center z-50 transition-all duration-300 animate-pop`}
    >
      <p className="text-lg text-slate-500 ">
        Are you sure you want to logout?
      </p>
      <div className="mt-4 w-full">
        <button className="font-bold rounded-lg w-full min-w-32 px-4 py-2  hover: bg-slate-400" onClick={() => {
            console.log("submitted...");
            setIsSubmited(true)
          }}>
            Logout
            </button>
          
        
        <button
          className=" font-bold rounded-lg w-full min-w-32 px-4 py-2 text-white bg-slate-600 hover:bg-standard_black my-2"
          onClick={() => doAppear(false)}
        >
          Cancel
        </button>
      </div>
    </div>
  );
};

export default Logout;