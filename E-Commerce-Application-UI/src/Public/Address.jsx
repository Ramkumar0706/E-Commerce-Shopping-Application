import React, { useEffect, useState } from "react";
import { RiLoader4Fill, RiUserLocationLine } from "react-icons/ri";
import axios from "axios";
import { useAuth } from "../Auth/AuthProvider";
import { Link, useLocation, useNavigate } from "react-router-dom";

const Address = () => {
  const location = useLocation(); 
  const [activeButton, setActiveButton] = useState(null);

  
  const { user } = useAuth();
  const { role, authenticated } = user;

  const navigate = useNavigate();
  const [addressId,setAddressId]=useState("");
  const [streetAddress, setStreetAddress] = useState("");
  const [streetAddressAdditional, setStreetAddressAdditional] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [addressType, setAddressType] = useState("");
  const [pincode, setPincode] = useState("");
  const [isSubmited, setIsSubmited] = useState(false);
  const [name, setName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
 

  console.log(streetAddress);
  console.log(streetAddressAdditional);
  console.log(city);
  console.log(pincode);
  console.log(addressType);
  console.log(addressId)
  const handleSubmit = async (e) => {
    
    e.preventDefault();
    console.log("Submitted success");
    const formData = {
      streetAddress: streetAddress,
      streetAddressAdditional: streetAddressAdditional,
      city: city,
      state: state,
      pincode: pincode,
      addressType: addressType,
    };
   const url=addressId? `http://localhost:8080/api/ecav1/updateAddress/${addressId}`:"http://localhost:8080/api/ecav1/addAddress"
    const creidt= {
      headers: {
        "Content-Type": "application/json",
      },
      withCredentials: true,
    };
    console.log(url)
    const response =addressId?await axios.put(url,formData,creidt):await axios.post(url,formData,creidt)
    
    if (response.status == 200) {
      sessionStorage.setItem("addressId", response.data.data.addressId);

      addressId?navigate("/myProfile"):navigate("/contact")
    }
    console.log(response.status);
  };
  const handleButtonClick = (buttonName) => {
    setActiveButton(buttonName);
    setAddressType(buttonName)
  };
  useEffect(() => {
    if (location.state) {
      const { streetAddress, streetAddressAdditional, city, state, addressType, pincode,addressId} = location.state;
      setAddressId(addressId)
      setStreetAddress(streetAddress);
      setStreetAddressAdditional(streetAddressAdditional);
      setCity(city);
      setState(state);
      setAddressType(addressType);
      setPincode(pincode);
    }
  }, [location.state]);

  return (
    <div className="flex items-center justify-center">
      <div className="flex flex-col justify-center items-center w-1/2 h-1/2 mt-14">
        <div
          className={`w-full h-max flex flex-col justify-center items-center text-slate `}
        >
          <FormHeading icon={<RiUserLocationLine />} text={"Address Details"} />
          <div className="w-full px-4 py-2 h-full">
            <div className="w-full flex justify-center items-center mb-4">
              <Input
                isRequired={true}
                onChangePerform={setStreetAddress}
                placeholderText={"Address Line 1: "}
                value={streetAddress}
              />
            </div>

            <div className="w-full flex justify-center items-center mb-4">
              <Input
                isRequired={true}
                onChangePerform={setStreetAddressAdditional}
                placeholderText={"Address Line 2 (optional): "}
                value={streetAddressAdditional}
              />
            </div>

            <div className=" flex justify-center items-center mb-4 w-full">
              <Input
                isRequired={true}
                onChangePerform={setCity}
                value={city}
                placeholderText={"Enter the City "}
              />
            </div>

            <div className="full mb-4">
              <Input
                isRequired={true}
                onChangePerform={setState}
                placeholderText={"Enter the state"}
                value={state}
              />
            </div>

            <div className="w-full flex justify-center items-center mb-4">
              <Input
                isRequired={true}
                onChangePerform={setPincode}
                placeholderText={"Enter the pincode "}
                value={pincode}
              />
            </div>
          </div>
          <div className="flex justify-center space-x-4 mb-5">
      <button
        className={`bg-blue-300 px-3 py-2 rounded-lg ${
          activeButton === "HOME" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
        }`}
        onClick={() => handleButtonClick("HOME")}
      >
        HOME
      </button>
      <button
        className={`bg-blue-300 px-3 py-2 rounded-lg  ${
          activeButton === "WORK" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
        }`}
        onClick={() => handleButtonClick("WORK")}
      >
        WORK
      </button>
    </div>
        </div>


        <button
          className="bg-emerald-300 w-full mb-10 py-2 px-3 rounded-lg bg-prussian_blue text-slate-950 hover:bg-blue-600   border-blue-500"
          onClick={handleSubmit}

        >
          {addressId?"update":"create"}
        </button>
      </div>
    </div>
  );
};
export default Address;

const SubmitBtn = ({  isSubmited, name, isActive, onClick }) => {
  return (
    <button
      onClick={onClick}
      disabled={isSubmited || isActive}
      className={`font-bold rounded-lg w-full min-w-32 px-4 py-2 border-2 ${
        isActive
          ? "bg-blue-600 text-white"
          : "bg-prussian_blue text-slate-950 hover:bg-blue-600   border-blue-500"
      }`}
      type="button"
    >
      {name}
    </button>
  );
};

const FormHeading = ({ icon, text }) => {
  return (
    <h1 className="text-slate-700 font-semibold text-4xl my-8 flex justify-start w-full">
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
