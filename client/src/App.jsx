import { Route, Routes, BrowserRouter as Router} from 'react-router-dom';
import Home from './pages/Home';
import Invites from './pages/Invites';
import NotFound from './pages/NotFound';
import Navbar from './components/Navbar';

function App() {

  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/invites" element={<Invites />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App
