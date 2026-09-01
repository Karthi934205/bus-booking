import Nav from "./Nav";
import Intro from "./Intro";
import Search from "./Search";
import Aside from "./Aside";
import Footer from "./Footer";
import Profile from "./Profile";

function Home() {
  return (
    <div>
      <Nav />
      <Intro />
      <Search />
      <Aside />
      <Footer />
    </div>
  );
}

export default Home;