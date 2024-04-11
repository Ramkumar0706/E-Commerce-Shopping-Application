import React from 'react'

function Register() {
  return (
    <div className=''>
      <form className='mt-32 mx-auto border-2 border-slate-400 w-96 h-60 flex flex-col justify-evenly items-center rounded-md scale-110'>
<div className='text-green-600 font-bold text-lg text-center w-full bg-yellow-2p0 -mt-3.5 rounded-t'>Sign Up</div>
<input className='border-2 border-slate-490 px-5 py-1' type="text" placeholder="Enter email"/>
<input className='border-2 border-slate-400 px-5 py-1' type='password' placeholder='Enter password'/>
<input className='border-2 border-slate-490 px-5 py-1' type='password' placeholder='Confirm password!'/>
<input className='border-2 border-green-500 px-5 py-1 cursor-pointer rounded-md currentColor hover:bg-green-500 hover: text-slate-100
transition ease-in-out duration-300 hover:border-green-500 hover:-translate-y-1' type='submit' value='Register'/>
</form>
    </div>
  )
}

export default Register