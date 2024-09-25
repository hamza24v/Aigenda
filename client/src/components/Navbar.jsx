import { NavLink } from "react-router-dom";
import { useUser } from "../contexts/UserContext";
import { useLocation } from "react-router-dom";
function Navbar() {
  const location = useLocation();
  const { user } = useUser(); 
  
  if (location.pathname === "/login" || location.pathname === "/register") {
    return null;
  }

  return (
    <nav className="relative w-full shadow-md p-4 z-50">
      <div className="flex justify-start text-xl items-center max-w-7xl mx-auto">
        <NavLink
          to="/"
          className={({ isActive }) =>
            isActive ? "text-blue-500 font-bold px-10" : "text-gray-500 px-10"
          }
        >
          Home
        </NavLink>
        <NavLink
          to="/invites"
          className={({ isActive }) =>
            isActive ? "text-blue-500 font-bold px-10" : "text-gray-500 px-10"
          }
        >
          Invites
        </NavLink>
        {user ? (

          <>
            <NavLink
              to="/my-calendar"
              className={({ isActive }) =>
                isActive ? "text-blue-500 font-bold px-10" : "text-gray-500 px-10"
              }
            >
              My Calendar
            </NavLink>
            <span className="ml-auto px-10 text-blue-500 font-bold">
              {user.username}
            </span>
          </>
        ) : (
          <NavLink
            to="/login"
            className={({ isActive }) =>
              isActive ? "text-blue-500 font-bold px-10" : "text-gray-500 px-10"
            }
          >
            Login
          </NavLink>
        )}
      </div>
    </nav>
  );
}

export default Navbar;