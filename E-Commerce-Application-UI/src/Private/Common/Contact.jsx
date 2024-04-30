import axios from 'axios';
import React, { useState } from 'react';
import { RiUserLocationLine } from 'react-icons/ri';

import {Link,  useNavigate } from 'react-router-dom';

function Contact() {
  const [name,setName]=useState('');
  const[phoneNumber,setPhoneNumber]=useState('');
  const[email,setEmail]=useState('');
  const[priority,setPriority]=useState('false')
const addressId=sessionStorage.getItem('addressId')
const navigate=useNavigate();
  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData={
      name:name,
      phoneNumber:phoneNumber,
      email:email,


    }
    const response = await axios.post(`http://localhost:8080/api/ecav1/address/addContact?addressId=${addressId}`,formData,{
      headers:{
        'Content-Type': 'application/json'
      },withCredentials:true
    }
   
    );
    if(response.status==200){
      navigate("/")
    }
    console.log(response.status
    )
  }
  return (
    <div className='w-full  flex justify-center bg-slate-300 h-full'>
    <div className='mt-16 w-1/2  '>
       <FormHeading icon={<RiUserLocationLine />} text={"Contact"} />
    <div className="w-full flex justify-center items-center mb-4">
          <Input
            isRequired={true}
            onChangePerform={setName}
            placeholderText={"Enter the name"}
            value={name}
          />
        </div>
        <div className="w-full flex justify-center items-center mb-4">
          <Input
            isRequired={true}
            onChangePerform={setPhoneNumber}
            placeholderText={"Enter the Phone number"}
            value={phoneNumber}
          />
        </div>
        <div className="w-full flex justify-center items-center mb-4">
          <Input
            isRequired={true}
            onChangePerform={setEmail}
            placeholderText={"Enter the email"}
            value={email}
          />
        </div>
        
      <button className=' w-full mb-10 py-2 px-3 rounded-lg bg-prussian_blue text-slate-950 hover:bg-blue-600   border-blue-500' 
      onClick={
        handleSubmit
      }>
        Submit

      </button></div>
      </div>
  )
}

export default Contact;

const FormHeading = ({ icon, text }) => {
  return (
    <h1 className="text-slate-700 font-semibold text-4xl my-8 flex justify-center w-full">
      <div className="mt-1 mr-2">{icon}</div>
      {text}
    </h1>
  );
};

const Input = ({value, onChangePerform, isRequired, placeholderText}) => {
  return (
    <input
      type="text"
      onChange={(event) => onChangePerform(event.target.value)}
      required={isRequired}
      placeholder={placeholderText}
      value={value? value : ""}
      className="border-2 border-transparent rounded-md bg-gray-100 w-full py-2  px-2 text-base hover:border-gray-300 focus:border-gray-300 placeholder:text-slate-500"
    />
  );
};