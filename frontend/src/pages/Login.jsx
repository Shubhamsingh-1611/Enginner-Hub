import { useState } from "react";
import { Link } from "react-router-dom";
import API from "../api/authApi.js"

export default function Login() {
  
  const [formData, setFormData] = useState({
    email: "",
    password: ""
});


const handleSubmit = async (e) => {

    e.preventDefault();

    try {

        console.log(formData);

        const response = await API.post(
            "/auth/login",
            formData
        );
       const {token,message} = response.data;;
       localStorage.setItem("token", token);
       console.log(response);
        alert(response.data.message);

    } catch (error) {

        console.log(error);

        alert("Registration Failed");

    }

};
  
  
  return (
    <div className="min-h-screen bg-white flex items-center justify-center px-5">

      <div className="grid lg:grid-cols-2 w-full max-w-6xl rounded-3xl shadow-2xl overflow-hidden">

        {/* Left Side */}
        <div className="hidden lg:flex flex-col justify-center bg-[#111111] text-white p-14">

          <h1 className="text-5xl font-bold leading-tight">
            Welcome to
            <span className="block text-[#F4D00A] mt-2">
              EngineerHub
            </span>
          </h1>

          <p className="mt-6 text-gray-300 text-lg leading-8">
            Buy, Sell and Connect with fellow engineers.
            Find projects, tools, books and opportunities
            all in one place.
          </p>

          <div className="mt-12 flex gap-3">
            <div className="w-3 h-3 rounded-full bg-[#F4D00A]" />
            <div className="w-3 h-3 rounded-full bg-[#FFEE8C]" />
            <div className="w-3 h-3 rounded-full bg-white" />
          </div>

        </div>

        {/* Right Side */}

        <div className="bg-white p-8 md:p-14">

          <h2 className="text-4xl font-bold text-[#111111]">
            Login
          </h2>

          <p className="text-gray-500 mt-2">
            Sign in to continue
          </p>

          <form className="mt-10 space-y-6">

            <div>
              <label className="text-sm font-medium text-gray-700">
                Email
              </label>

              <input
                type="email"
                placeholder="Enter email"
                  value={formData.email}
                  onChange={(e) =>
                    setFormData((prev) => ({
                      ...prev,
                      email: e.target.value,
                    }))
                  }
                className="w-full mt-2 rounded-xl border border-gray-300 px-4 py-3 focus:outline-none focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] transition"
              />
            </div>

            <div>
              <label className="text-sm font-medium text-gray-700">
                Password
              </label>

              <input
                type="password"
                placeholder="Enter password"
                  value={formData.password}
                  onChange={(e) =>
                    setFormData((prev) => ({
                      ...prev,
                      password: e.target.value,
                    }))
                  }
                className="w-full mt-2 rounded-xl border border-gray-300 px-4 py-3 focus:outline-none focus:border-[#F4D00A] focus:ring-4 focus:ring-[#FFEE8C] transition"
              />
            </div>

            <div className="flex justify-between items-center text-sm">

              <label className="flex items-center gap-2">
                <input type="checkbox" />
                Remember me
              </label>

              <a
                href="/forgot-password"
                className="text-[#111111] hover:text-[#F4D00A]"
              >
                Forgot Password?
              </a>

            </div>

            <button
              onClick={handleSubmit}
              className="w-full bg-[#111111] hover:bg-[#F4D00A] hover:text-black text-white font-semibold py-3 rounded-xl transition duration-300"
            >
              Login
            </button>

          </form>

          <p className="text-center mt-8 text-gray-600">
            Don't have an account?

            <Link
              to="/register"
              className="ml-2 font-semibold text-[#111111] hover:text-[#F4D00A]"
            >
              Register
            </Link>

          </p>

        </div>

      </div>

    </div>
  );
}