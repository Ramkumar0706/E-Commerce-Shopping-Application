import React from 'react'

function Login() {
  return (
    <div>
      <form className='mt-32 mx-auto border-2 border-slate-4008 w-96 h-60 flex flex-col justify-evenly items-center rounded-md
    scale-110'>
    <div className='text-yellow-800 font-bold text-lg text-center w-full bg-green-300 -mt-4 rounded-t'>Welcome Back!</div>
    
    <input className='border-2 border-slate-40@ px-5 py-1' type="text" placeholder='Enter email'/>
    <input className='border-2 border-slate-400 px-5 py-1' type="password" placeholder='Enter password'/>
    <input className="border-2 border-yellow-400 px-5 py-1 cursor-pointer rounded-md font-medium transition ease—in-out
    duration-300 hover:bg-yellow-200 hover: text-sky-708 hover: border-yellow-200 hover:-translate-y-1" type="submit" value='Log In'/>
    <a className='text-base font-normal' href=''>forgot password?</a>
    </form>
    </div>
  )
}

export default Login