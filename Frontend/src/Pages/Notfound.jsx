import "../Styles/Notfound.css"
import { Link, useLocation } from "react-router-dom";

export default function NotFound() {
  const location = useLocation();
  // Only show Navigation component when not on the NotFound page
  // const showNavigation = location.pathname !== "*";

  console.log("not found ---- path",location.pathname)

  return (
    <>
      <div style={{ height: '65px' }}></div>
      <section className='page_404'>
        <link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Arvo'/>
        <div className="error_container">
          <div>
            <div>
              <div className="text-center">
                <div className="four_zero_four_bg">
                  <h1 className="text-center ">404</h1>
                </div>
                <div className="contant_box_404">
                  <h3 className="h2">Look like you're lost</h3>
                  <p>the page you are looking for is not avaible!</p>
                  <Link to={'/'} className="link_404">Go to Home</Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  )
}
