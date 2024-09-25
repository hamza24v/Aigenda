import { NavLink } from "react-router-dom";
import { useUser } from '../contexts/UserContext';

function Navbar() {
  const { user } = useUser();
  if (!user) return null;
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
      </div>
    </nav>
  )
}

export default Navbar