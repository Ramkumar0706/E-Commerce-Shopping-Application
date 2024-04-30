import axios from "axios";
import React, { useEffect, useState } from "react";

function ManageAddress() {
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

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/ecav1/findAddress", "", {
        headers: {
          "Content-Type": "application/json",
        },
        withCredentials: true,
      })
      .then((response) => {
        const data = response.data;
        setStreetAddress(data.streetAddress);
        setStreetAddressAdditional(data.streetAddressAdditional);
        setCity(data.city);
        setState(data.state);
        setAddressType(data.addressType);
        setPincode(data.pincode);
        setIsSubmited(data.isSubmited);
        setName(data.name);
        setPhoneNumber(data.phoneNumber);
        setEmail(data.email);
      })
      .catch((error) => console.error("Error fetching data:", error));
  }, []);

  return (
    <div className="mt-16">
      <h2>Address Details</h2>
      <p>Street Address: {streetAddress}</p>
      <p>Street Address Additional: {streetAddressAdditional}</p>
      <p>City: {city}</p>
      <p>State: {state}</p>
      <p>Address Type: {addressType}</p>
      <p>Pincode: {pincode}</p>
      <p>Submitted: {isSubmited ? "Yes" : "No"}</p>
      <h2>User Details</h2>
      <p>Name: {name}</p>
      <p>Phone Number: {phoneNumber}</p>
      <p>Email: {email}</p>
    </div>
  );
}

export default ManageAddress;
