import { Route, Routes, BrowserRouter as Router } from "react-router-dom";
import Home from "./pages/Home";
import Invites from "./pages/Invites";
import NotFound from "./pages/NotFound";
import Navbar from "./components/Navbar";
import LandingPage from "./pages/LandingPage";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { UserProvider } from "./contexts/UserContext"; // this is needed to protect our routes

function App() {
  return (
    <div className="w-full">
<UserProvider>
      <Router>
        <Navbar />

        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/home" element={<Home />} />
          <Route path="/invites" element={<Invites />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </Router>
    </UserProvider>

    </div>
    
  );
}

export default App;
