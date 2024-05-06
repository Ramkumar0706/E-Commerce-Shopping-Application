import React, { useState } from "react";
import { Link } from "react-router-dom";
import { GoHeart } from "react-icons/go";
import { PiDotsThreeVerticalBold, PiUserCircle } from "react-icons/pi";
import { PiStorefront } from "react-icons/pi";
import { IoSearchOutline } from "react-icons/io5";
import { MdLogout } from "react-icons/md";
import { MdKeyboardArrowUp } from "react-icons/md";
import { MdKeyboardArrowDown } from "react-icons/md";
import { TbBoxSeam } from "react-icons/tb";
import { FaRegBell } from "react-icons/fa6";
import { PiHeadset } from "react-icons/pi";
import { BsDownload } from "react-icons/bs";
import { RxDashboard } from "react-icons/rx";
import { IoCartOutline } from "react-icons/io5";
import { LuBoxes } from "react-icons/lu";
import { useAuth } from "../Auth/AuthProvider";
import Logout from "../Private/Common/Logout";
import { RiUserLocationLine } from "react-icons/ri";
const Headers = () => {
  const { user } = useAuth();

  const { username, role, authenticated } = user;
  console.log(user);
  // console.log(user)

  const [loginHovered, setLoginHovered] = useState(false);
  const [optionHovered, setoptionHovered] = useState(false);
  const [doLogout, setDoLogout] = useState(false);

  return (
    <header className="border-b-2 border-blue-900 fixed z-50 top-0 font-sans w-screen flex justify-center bg-white">
      <div className="flex justify-around items-center w-1/2 ">
        {/* filpkart logo */}
        <div>
          <Link to={"/"}>
            <img src="/src/Images/flipkart-logo.svg" alt="flipkart logo" />
          </Link>
        </div>
        {/* Search Bar */}
        <div className="rounded-md h-4/6 w-1/2 text-lg flex items-center justify-center bg-blue-50 px-2 py-1">
          <div className="text-2xl text-slate-500">
            <IoSearchOutline />
          </div>
          <input
            type="text"
            placeholder="Search the Product Here!..."
            className="border-0 rounded-xl bg-blue-50 placeholder:text-slate-500 hover:placeholder:text-slate-400 h-full outline-none px-2 py-2 w-full  text-gray-700"
          />
        </div>
      </div>
      {doLogout && <Logout doAppear={setDoLogout} />}
      {/* login and Register */}
      <div className="flex justify-around items-center w-1/2  ">
        <div
          className=" flex justify-around items-center  hover:bg-blue-500 rounded-md py-2 "
          onMouseEnter={() => setLoginHovered(true)}
          onMouseLeave={() => setLoginHovered(false)}
        >
          <div>
            <PiUserCircle />
          </div>
          <Link to={authenticated ? "/account" : "/login"}>
            {authenticated ? username : "Login"}
          </Link>

          <div>
            {loginHovered ? <MdKeyboardArrowUp /> : <MdKeyboardArrowDown />}
          </div>

          {loginHovered ? (
            <div className="shadow-lg shadow-slate-300 bg-white rounded-md  h-max absolute   top-12 w-1/6 translate-x-1/3 -translate-y-0.5 flex flex-col justify-center transition-all ">
              <div className="flex justify-between items-center w-full border-b-2 border-slate-300 p-2">
                <p className="text-slate-700 hover:cursor-pointer ">
                  {authenticated ? "MyProfile" : "New Customer"}
                </p>
                <Link
                  to={authenticated ? "/myProfile" : "/customer/register"}
                  className="text-blue-800"
                >
                  {authenticated ? "Click Here" : "Signup"}
                </Link>
              </div>
              <div className="py-2">
                <HeaderLink
                  className="text-prussian_blue font-semibold rounded-sm py-4"
                  to=""
                  name="Orders"
                  icon={<TbBoxSeam />}
                />
                <div className="py-2">
                  <HeaderLink
                    className="text-prussian_blue font-semibold rounded-sm px-2"
                    to=""
                    name="WishList"
                    icon={<GoHeart />}
                  />
                  {authenticated ? (
                    <div>
                      {/* <HeaderLink on  className="mt-12" link="/logout" icon={<MdLogout />} name={"Logout"} /> */}

                      <button
                        className="flex  items-center px-2 "
                        onClick={() => setDoLogout(true)}
                      >
                        <div className="mt-0.5  hover: text-2xl">
                          <MdLogout />
                        </div>{" "}
                        <h3>Logout</h3>{" "}
                      </button>
                    </div>
                  ) : (
                    ""
                  )}
                </div>
              </div>
            </div>
          ) : (
            ""
          )}
        </div>
        {authenticated && role === "CUSTOMER" ? (
          <div className="flex items-center justify-evenly  w-1/2 ">
            <HeaderLink icon={<IoCartOutline />} name={"Cart"} link={"/cart"} />
            <HeaderLink
              icon={<GoHeart />}
              name={"WIshlist"}
              link={"/wishlist"}
            />
          </div>
        ) : authenticated && role === "SELLER" ? (
          <div className="flex items-center justify-evenly  w-1/2 ">
          
          <HeaderLink icon={<RxDashboard />} link={"/sellerDashBoard"} name={"DashBoard"}/>
          <HeaderLink icon={<LuBoxes />} link={"/orders"} name={"Orders"} />
          </div>
        ) : (
          !authenticated && (
            <HeaderLink
              icon={<PiStorefront />}
              name={"Become a Seller"}
              link={authenticated ? "/store" : "/seller/register"}
            />
          )
        )}
        <div></div>

        <div
          onMouseEnter={() => setoptionHovered(true)}
          onMouseLeave={() => setoptionHovered(false)}
        >
          <HeaderLink icon={<PiDotsThreeVerticalBold />} name={""} link={""} />

          {optionHovered ? (
            <div className="shadow-lg shadow-slate-300 bg-white rounded-sm h-max absolute right-0  w-1/6 translate-x-2 -translate-y-0.5 flex flex-col justify-center transition-all duration-1000">
              <HeaderLink
                to=""
                name="NotificationPreference"
                icon={<FaRegBell />}
              />
              <HeaderLink
                to="/verify-otp"
                name="24x7 CustomerService"
                icon={<PiHeadset />}
              />
              <HeaderLink to="" name="DownloadApp" icon={<BsDownload />} />
            </div>
          ) : (
            ""
          )}
        </div>
      </div>
    </header>
  );
};

export default Headers;

const HeaderLink = ({ link, name, icon }) => {
  return (
    <div>
      <Link to={link} className="flex">
        <div className="mt-0.5 mr-1 hover: text-2xl"> {icon}</div>
        <div className="px-1 flex justify-center items-center">{name}</div>
      </Link>
    </div>
  );
};
