import { NavLink, useNavigate } from "react-router-dom";
import { useUser } from "../contexts/UserContext";
import { useLocation } from "react-router-dom";
function Navbar() {
  const location = useLocation();
  const { user, setUser } = useUser();
  const navigate = useNavigate();

  if (location.pathname === "/login" || location.pathname === "/register") {
    return null;
  }

  const handleClick = () => {
    navigate("/");
    console.log("HELLO");
  };

  const handleLogout = () => {
    setUser(null);
    navigate("/login");
  };

  return (
    <nav className="relative w-full shadow-md p-4 z-50">
      <div
        className={`flex justify-${
          user ? "start" : "between"
        } text-xl items-center mx-auto`}
      >
        <div
          onClick={handleClick}
          className="cursor-pointer flex items-center justify-between"
        >
          <img
            className="w-10 h-10"
            src="/calendar-icon.png"
            alt="Calendar Icon"
          />
          <p className="text-3xl pl-1 font-semibold font-sans text-blue-600">AiGenda</p>
        </div>
        ;
        {user ? (
          <div className="ml-[18%]">
            <NavLink
              to="/home"
              className={({ isActive }) =>
                isActive
                  ? "text-blue-500 font-bold px-10"
                  : "text-gray-500 px-10"
              }
            >
              Home
            </NavLink>
            <button
              onClick={handleLogout}
              class=" text-white font-bold py-2 px-4 rounded transition ease-in-out delay-150 bg-red-500 hover:-translate-y-1 hover:scale-110 hover:bg-red-700 duration-300 ml-[65%]"
            >
              Logout
            </button>
          </div>
        ) : (
          <div className="flex justify-right items-center ">
            <div className="text-lg font-medium text-gray-700 space-x-4  hover:text-gray-900 rounded-lg p-2 transition-all duration-300 cursor-pointer">
              <NavLink
                to="/login"
                className="hover:text-blue-400 rounded-lg p-1"
              >
                Login
              </NavLink>
              <NavLink
                to="/register"
                className="bg-gray-500 text-white hover:text-blue-200 rounded-lg p-1"
              >
                Register
              </NavLink>
            </div>
          </div>
        )}
      </div>
    </nav>
  );
}

export default Navbar;
