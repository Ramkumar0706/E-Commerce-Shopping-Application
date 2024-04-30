import React from 'react'
import { Link } from 'react-router-dom';
import { RiGitRepositoryCommitsFill, RiUserLocationLine } from "react-icons/ri";
import { FaRegAddressBook } from 'react-icons/fa6';
function MyProfile() {
  return (
    <div className='mt-16  w-1/3'>
        <FormHeading icon={<RiUserLocationLine />} text={"User Details"} />
     <HeaderLink link={"/manageAddress"} name={"ManageAddress"} icon={<RiGitRepositoryCommitsFill />}/>
     <HeaderLink link={"/address"} name={"AddAddress"} icon={<FaRegAddressBook />}/>
     
    </div>
  )
}

export default MyProfile
const HeaderLink = ({ link, name, icon }) => {
    return (
      <div className=' mb-3 ml-4'>
        <Link to={link} className="flex bg-blue-300  px-3 py-4 rounded-lg hover:bg-blue-700">
          <div className="mt-0.5 mr-1 hover: text-2xl"> {icon}</div>
          <div className="px-1 flex justify-center items-center">{name}</div>
        </Link>
      </div>
    );
  };

  const FormHeading = ({ icon, text }) => {
    return (
      <h1 className="text-slate-500 font-semibold text-2xl my-8 flex justify-center w-full">
        <div className="mt-1 mr-2">{icon}</div>
        {text}
      </h1>
    );
  };