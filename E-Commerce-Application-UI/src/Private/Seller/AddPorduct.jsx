import React from "react";

function AddPorduct() {
  return (
    <div className="mt-16 flex justify-center ">
      <div className="bg-blue-200 w-1/2  rounded-xl p-6">
        <h1 className="text-4xl text-slate-700">Add Product Details</h1>
        <div className="relative mt-10">
          <input
            id="username"
            name="username"
            type=""
            className=" rounded-xl  peer placeholder-transparent h-12  w-full border-b-2 focus:outline-none "
            placeholder="Password"
          />
          <label
            for="username"
            className=" absolute left-0 -top-3.5 text-gray-600 text-xl peer-placeholder-shown:text-xl peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
          >
            Enter Product Brand
          </label>
        </div>
        <div className="relative mt-10">
          <input
            id="username"
            name="username"
            type=""
            className=" rounded-xl  peer placeholder-transparent h-12  w-full border-b-2 focus:outline-none "
            placeholder="Password"
          />
          <label
            for="username"
            className=" absolute left-0 -top-3.5 text-gray-600 text-xl peer-placeholder-shown:text-xl peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
          >
            Enter Product Model
          </label>
        </div>

        <div className="relative mt-10">
          <input
            id="username"
            name="username"
            type=""
            className=" rounded-xl  peer placeholder-transparent h-12  w-full border-b-2 focus:outline-none "
            placeholder="Password"
          />
          <label
            for="username"
            className=" absolute left-0 -top-3.5 text-gray-600 text-xl peer-placeholder-shown:text-xl peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
          >
            Enter Product Discreption
          </label>
        </div>
        {/* price and Quanitty */}
        <div className="flex justify-between ">
          <div className="relative mt-10 w-1/3 ml-5">
            <input
              id="username"
              name="username"
              type=""
              className=" rounded-xl  peer placeholder-transparent h-12  w-full border-b-2 focus:outline-none "
              placeholder="Password"
            />
            <label
              for="username"
              className=" absolute left-0 -top-3.5 text-gray-600 text-xl peer-placeholder-shown:text-xl peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
            >
              Price
            </label>
          </div>

          <div className="relative mt-10  w-1/3 mr-5">
            <input
              id="username"
              name="username"
              type=""
              className=" rounded-xl  peer placeholder-transparent h-12  w-full border-b-2 focus:outline-none "
              placeholder="Password"
            />
            <label
              for="username"
              className=" absolute left-0 -top-3.5 text-gray-600 text-xl peer-placeholder-shown:text-xl peer-placeholder-shown:text-gray-440 hover:cursor-pointer
              peer-placeholder-shown:top-2 transition-all peer-focus:-top-3.5 peer-focus:text-gray-600 peer-focus:text-sm"
            >
              Quanitity
            </label>
          </div>

          
        </div>
        <div className="flex justify-center space-x-4 mb-5 mt-6">
            <button
              className={`bg-blue-300 px-3 py-2 rounded-lg `}
              // ${
              //   activeButton === "PRIMARY" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
              // }`}
              // onClick={() => handleButtonClick("PRIMARY")}
            >
              MOBILE
            </button>
            <button
              className={`bg-blue-300 px-3 py-2 rounded-lg  `}
              // ${
              //   activeButton === "OPTIONAL" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
              // }`}
              // onClick={() => handleButtonClick("OPTIONAL")}
            >
              LAPTOP
            </button>

            <button
              className={`bg-blue-300 px-3 py-2 rounded-lg  `}
              // ${
              //   activeButton === "OPTIONAL" ? "bg-blue-700 hover:bg-blue-700" : "hover:bg-gray-500"
              // }`}
              // onClick={() => handleButtonClick("OPTIONAL")}
            >
              TV'S
            </button>
          </div>
          <div className="flex justify-center mb-10 ">
            <button className="rounded-xl bg-slate-500 py-3 w-1/3 hover:bg-slate-700"> Submit</button>
            </div>
          
      </div>
    </div>
  );
}

export default AddPorduct;
