import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function ManageAddress() {

  const [contactId,setContactId]=useState("")
  const [name, setName] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [email, setEmail] = useState("");
  const [priority, setPriority] = useState("PRIMARY");
  const [streetAddress, setStreetAddress] = useState("");
  const [streetAddressAdditional, setStreetAddressAdditional] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [addressType, setAddressType] = useState("");
  const [pincode, setPincode] = useState("");
  const [isSubmited, setIsSubmited] = useState(false);
  const[addressId,setAddressId]=useState("");
  const [contacts, setContacts] = useState([
    {
      contactId:"",
      name: "",
      phoneNumber: "",
      email: "",
      priority:""
    },
  ]);

  const navigate = useNavigate();
  const handleEditAddress = () => {
    navigate("/address", {
      state: {
        streetAddress,
        streetAddressAdditional,
        city,
        state,
        addressType,
        pincode,
        addressId
      }
    });
    
    
  };
  const handleEditContact = (contact) => {
      navigate("/contact", {
        state: {
          contactId:contact.contactId, // assuming you have a unique identifier for each contact
          name: contact.name,
          phoneNumber: contact.phoneNumber,
          email: contact.email,
          priority: contact.priority,
        }
      });
    };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/ecav1/findAddress",
          {
            headers: {
              "Content-Type": "application/json",
            },
            withCredentials: true,
          }
        );
        console.log(response.data.data);
      
          setStreetAddress(response.data.data.streetAddress),
            setStreetAddressAdditional(
              response.data.data.streetAddressAdditional
            ),
            setCity(response.data.data.city),
            setAddressId(response.data.data.addressId),
            setState(response.data.data.state),
            setAddressType(response.data.data.addressType),
            setPincode(...pincode, response.data.data.pincode);
       

        if (Array.isArray(response.data.data.contacts)) {
          const updatedContacts = response.data.data.contacts.map((ele) => ({
            contactId:ele.contactId,
            name: ele.name,
            phoneNumber: ele.phoneNumber,
            email: ele.email,
          }));

          setContacts(updatedContacts);
        } else {
          setContacts([]);
        }
      } catch (error) {
        console.error(error);
      }
    };
    fetchData();
  }, []);
  //console.log(response.data)
  return (
    <div className="mt-16">
      <h2>Address Details</h2>
      <div className="flex ">
        <div className="flex justify-evenly items-center w-1/2 -ml-16">
          <div>
            <p>Street Address: {streetAddress}</p>
            <p>Street Address Additional: {streetAddressAdditional}</p>
          </div>
          <div>
            <p>City: {city}</p>
            <p>State: {state}</p>
          </div>
          <div>
            <p>Address Type: {addressType}</p>
            <p>Pincode: {pincode}</p>
          </div>
        </div>
        <div className="ml-10 flex justify-center items-center ">
          <button className="bg-slate-400 hover:bg-slate-600 px-5 py-2 rounded-xl  -ml-10"
          onClick={handleEditAddress}
          
          >
            Edit
          </button>
        </div>
      </div>
      <h2>User Details</h2>
      {contacts.length > 0 ? (
        contacts.map((c, index) => (
          <div className="flex justify-evenly items-center w-1/2">
            <div key={index} className="flex  justify-between items-center border-2">
              <h1>Contact No: {index + 1}</h1>
              <p>Name: {c.name}</p>
              <p>Phone Number: {c.phoneNumber}</p>
              <p>Email: {c.email}</p>
              </div>
              <button className="bg-slate-400 hover:bg-slate-600 px-5 py-2 rounded-xl  ml-20"
              onClick={() => handleEditContact(c)}
              >
                Edit
              </button>
            
          </div>
        ))
      ) : (
        <p>No contacts found.</p>
      )}
    </div>
  );
}

export default ManageAddress;
