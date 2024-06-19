import React, { useState } from "react";

import {Link,  useNavigate } from 'react-router-dom';
import axios from 'axios';
import { useAuth} from "../Auth/AuthProvider";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const { user ,setUser} = useAuth();
  const navigate=useNavigate();
 console.log(user)
  const handleSubmit = async (e) => {
    e.preventDefault();
    
    const formData = {
      username: username,
      password: password
    };

    try{
    // console.log(username+" "+password)
      const response = await axios.post('http://localhost:8080/api/ecav1/user/login',formData,{
      headers:{
        'Content-Type': 'application/json'
      },withCredentials:true
    }
    );
     
    if((await response).status==200) 
    {
      const userData={
        userId:(await response).data.data.userId,
        username:(await response).data.data.username,
        role:(await response).data.data.role,
        authenticated:true,
        accessExpiration:new Date(new Date().getTime()+(await response).data.data.accessExpiration),
        refreshExpiration:new Date(new Date().getTime()+(await response).data.data.refreshExpiration),
      };
      localStorage.setItem("authResponse",JSON.stringify((userData)));
      setUser(userData);

      navigate("/");
    }
    
  }
  catch(error) {
    if (error.response) {
      // Server responded with a status code outside 2xx range
      console.error('Server error:', error.response.data, error.response.status);
    } else if (error.request) { 
      // Server didn't respond 
      console.error('Request failed, could not reach server.'); 
    } else {
      // Error setting up the request
      console.error('Error creating request:', error.message);
    }
  }
  };
  return (
    <div className="flex min-h-screen items-center justify-center bg-gray-100 shadow-lg">
      <div className="justify-center bg-blue-500 w-1/5 shadow-md rounded-sm">
        <h1 className="mt-8 px pl-10 text-white text-3xl ">Login</h1>
        <p className="mt-8 pl-10 text-rose-100 text-lg">
          Get access to your Orders, Wishlist and Recommendations
        </p>
        <img
          className=" pl-12 mt-40 mb-10"
          src="/src/Images/login bottom logo.png"
          alt="images"
        />
      </div>
      <form onSubmit={handleSubmit} className='bg-white w-1/3 shadow-md rounded-sm p-10 h-full '>
      <div className=" bg-white w-full shadow-md rounded-sm  h-full ">
        <div className="relative mt-5">
          <input
           
            id="username"
            name="username"
            type=""
            className=" peer placeholder-transparent h-10 w-full border-b-2 focus:outline-none "
            placeholder="Password"
            value={username}
            onChange={(e) => {
              setUsername(e.target.value);
            }}
          />
          <label
            for="username"
            className="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
          >
            Enter your Email
          </label>
        </div>
        <div className="relative mt-5">
          <input
           
            id="password"
            name="password"
            type=""
            className=" peer placeholder-transparent h-10 w-full border-b-2 focus:outline-none "
            placeholder="Password"
            value={password}
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
          <label
            for="password"
            className="absolute left-0 -top-3.5 text-gray-600 text-sm peer-placeholder-shown:text-base peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
          >
            Enter your password
          </label>
        </div>
        <div className="mt-8 text-xs mb-5 input-focus:translate-y-20">
          By continuing, you agree to Filpkart's{" "}
          <a className="text-blue-500" href="">
            Terms
          </a>{" "}
          and{" "}
          <a className="text-blue-500" href="">
            Privicy Policy
          </a>
        </div>
        <button className="bg-orange-600 rounded-xl p-3 text-white w-full mb-36">
          Submit
        </button>
        <div className=" flex justify-center w-full">
          <Link className="text-blue-500 justify-center mt-4" to={"/register"}>
            New To Flipkart? Create a account
          </Link>
        </div>
      </div>
      </form>
    </div>
    
  );
}

export default Login;
