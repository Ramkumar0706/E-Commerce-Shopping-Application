import axios from "axios";
import React, { useEffect, useState } from "react";
import { RiUserLocationLine } from "react-icons/ri";

import { Link, useLocation, useNavigate } from "react-router-dom";

function Contact() {
  const location = useLocation(); 
  const [activeButton, setActiveButton] = useState(null);
  const [contactId,setContactId]=useState("")
  const [name, setName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
  const [priority, setPriority] = useState("PRIMARY");
  const addressId = sessionStorage.getItem("addressId");
  const navigate = useNavigate();
  useEffect(() => {
    if (location.state) {
      console.log("Location state:", location.state); // Debugging statement
      const { contactId, name, phoneNumber, email, priority } = location.state;
      setContactId(contactId);
      setName(name);
      setPhoneNumber(phoneNumber);
      setEmail(email);
      setPriority(priority);
    }
  }, [location.state]);
  
  console.log(contactId)
  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = {
      name: name,
      phoneNumber: phoneNumber,
      email: email,
      priority:priority
    };
     const url=contactId? `http://localhost:8080/api/ecav1/updateContact/${contactId}`:
     `http://localhost:8080/api/ecav1//address/addContact?addressId=${addressId}`
    
     const creidt= {
        headers: {
          "Content-Type": "application/json",
        },
        withCredentials: true,
      }
      const response =contactId?await axios.put(url,formData,creidt):await axios.post(url,formData,creidt)
    
    if (response.status == 200) {
      contactId?navigate("/myProfile"):navigate("/contact")
    }
    console.log(response.status);
  };
  const handleButtonClick = (buttonName) => {
    setActiveButton(buttonName);
    setPriority(buttonName)
  };
  console.log(priority)
  return (
    <div className="w-full  flex justify-center bg-slate-300 h-full">
      <div className="mt-16 w-1/2  ">
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
        <div className="flex justify-center space-x-4 mb-5">
      <button
        className={`bg-blue-300 px-3 py-2 rounded-lg ${
          activeButton === "PRIMARY" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
        }`}
        onClick={() => handleButtonClick("PRIMARY")}
      >
        PRIMARY
      </button>
      <button
        className={`bg-blue-300 px-3 py-2 rounded-lg  ${
          activeButton === "OPTIONAL" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
        }`}
        onClick={() => handleButtonClick("OPTIONAL")}
      >
        OPTIONAL
      </button>
    </div>
        <button
          className=" w-full mb-10 py-2 px-3 rounded-lg bg-emerald-300 text-slate-950 hover:bg-blue-600   border-blue-500"
          onClick={handleSubmit}
        >
          Submit
        </button>
      </div>
    </div>
  );
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

const Input = ({ value, onChangePerform, isRequired, placeholderText }) => {
  return (
    <input
      type="text"
      onChange={(event) => onChangePerform(event.target.value)}
      required={isRequired}
      placeholder={placeholderText}
      value={value ? value : ""}
      className="border-2 border-transparent rounded-md bg-gray-100 w-full py-2  px-2 text-base hover:border-gray-300 focus:border-gray-300 placeholder:text-slate-500"
    />
  );
};
