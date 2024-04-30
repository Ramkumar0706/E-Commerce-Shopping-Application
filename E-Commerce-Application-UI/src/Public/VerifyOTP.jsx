import React, { useState } from 'react'
import {  useNavigate } from 'react-router-dom';
import axios from 'axios';

const VerifyOTP=()=> {
  const [otp, setOtp] = useState();
  const navigate=useNavigate();
  const email=sessionStorage.getItem('email')
  console.log('ram')
  console.log(email,otp)
const onchangeName=(e)=>{
  const t=e.target.value
  setOtp(t)
}



  const handleSubmit= async (e)=>{
    e.preventDefault();
    const formData={
      email:email,
      otp:otp

    } 

  try{
    console.log("rk"+email+otp)
    const response = await axios.post('http://localhost:8080/api/ecav1/verify-email',formData);
    console.log(response.status)
    if(response.status==201){
     navigate('/')
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
}
  return (
    
    <div className=' flex justify-center  mt-20 '>
      <div className='bg-slate-300 w-1/3 shadow-md rounded-lg p-10 h-full'> 
      <h2 className='flex justify-center text-xl'>Enter the OTP </h2>
     <form  className='flex flex-col justify-center items-center mt-5   w-full' onSubmit={handleSubmit}>
      <div className='relative mt-5  w-full '>
     <input
          autoComplete='off'
          className=" peer placeholder-transparent h-10 w-full border-b-2 focus:outline-none  rounded-xl"
            type="text"
            id="otp"
            name="otp"
            value={otp}
            onChange={onchangeName}
          />
          <label for="otp" className="absolute left-0  text-gray-600 text-xl peer-placeholder-shown:text-xl peer-placeholder-shown:text-gray-440 
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm mx-2 ">Enter Email/Mobile number</label>
   </div>
      <button className='bg-orange-600 rounded-xl p-3 text-white w-full mt-10'>Submit</button>
     </form>
    </div>
    </div>
  )
}

export default VerifyOTP