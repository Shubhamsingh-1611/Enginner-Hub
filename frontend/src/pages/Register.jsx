import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import API from "../api/authApi.js"

export default function Register() {

  const navigate = useNavigate();


  const [formData, setFormData] = useState({ firstName: "", lastName: "", username: "", email: "", phone: "", password: "" });
   
  const handleSubmit = async (e) => {

    e.preventDefault();

    try {
       console.log(formData);

        const response = await API.post(
            "/auth/register",
            formData
        );
   console.log("send data");
        alert(response.data);
        navigate("/login");
         
    } catch (error) {

        console.log(error);

        alert("Registration Failed");

    }

};
 
  return (

    <div className="min-h-screen bg-white flex items-center justify-center px-5">

      <div className="grid lg:grid-cols-2 w-full max-w-6xl rounded-3xl shadow-2xl overflow-hidden">

        {/* Left */}

        <div className="hidden lg:flex bg-[#111111] text-white flex-col justify-center p-14">

          <h1 className="text-5xl font-bold">
            Join
            <span className="block text-[#F4D00A] mt-2">
              EngineerHub
            </span>
          </h1>

          <p className="mt-6 text-gray-300 leading-8">
            Create your account and become a part of India's
            engineering marketplace.
          </p>

        </div>

        {/* Right */}

        <div className="bg-white p-8 md:p-14">

          <h2 className="text-4xl font-bold text-[#111111]">
            Register
          </h2>

          <p className="mt-2 text-gray-500">
            Create your account
          </p>

          <form className="mt-10 grid gap-5">

            <div className="grid md:grid-cols-2 gap-5">

              <input
                placeholder="First Name"
               
              value={formData.firstName}
              onChange={(e) =>
              setFormData((prev) => ({
                ...prev,
                firstName: e.target.value,
              }))
              }

                className="border border-gray-300 rounded-xl px-4 py-3 focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] outline-none"
              />

              <input
                placeholder="Last Name"
                alue={formData.lastName}
              onChange={(e) =>
              setFormData((prev) => ({
                ...prev,
                lastName: e.target.value,
              }))
              }
                className="border border-gray-300 rounded-xl px-4 py-3 focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] outline-none"
              />

            </div>

            <input
              placeholder="Username"
              alue={formData.username}
              onChange={(e) =>
              setFormData((prev) => ({
                ...prev,
                username: e.target.value,
              }))
              }
              className="border border-gray-300 rounded-xl px-4 py-3 focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] outline-none"
            />

            <input
              type="email"
              alue={formData.email}
              onChange={(e) =>
              setFormData((prev) => ({
                ...prev,
                email: e.target.value,
              }))
              }
              placeholder="Email"
              className="border border-gray-300 rounded-xl px-4 py-3 focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] outline-none"
            />

            <input
            alue={formData.phone}
              onChange={(e) =>
              setFormData((prev) => ({
                ...prev,
                phone: e.target.value,
              }))
              }
              placeholder="Phone"
              className="border border-gray-300 rounded-xl px-4 py-3 focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] outline-none"
            />

            <input
              type="password"
              alue={formData.password}
              onChange={(e) =>
              setFormData((prev) => ({
                ...prev,
                password: e.target.value,
              }))
              }
              placeholder="Password"
              className="border border-gray-300 rounded-xl px-4 py-3 focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] outline-none"
            />

            <button
              onClick={handleSubmit}
              className="bg-[#111111] hover:bg-[#F4D00A] hover:text-black text-white font-semibold py-3 rounded-xl transition duration-300"
            >
              Create Account
            </button>

          </form>

          <p className="mt-8 text-center text-gray-600">
            Already have an account?

            <Link
              to="/login"
              className="ml-2 font-semibold text-[#111111] hover:text-[#F4D00A]"
            >
              Login
            </Link>

          </p>

        </div>

      </div>

    </div>
  );
}