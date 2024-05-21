import { useNavigate } from "react-router-dom"
import { useAuth } from "../security/AuthContext"
import { Toaster, toast } from "react-hot-toast"

const HeaderComponent = () => {

    const navigate = useNavigate()
    const {logout, isAuthenticated, isAthlete} = useAuth()
    const successToast = (message) => toast.success(message)

    const handleLogoutBtn = () => {
        logout()
        navigate(`/`)
        successToast('Logged out succesfully.')
    }
    const handleStravaBtn = () => {
        navigate(`/athlete/strava`)
    }
    const handleAthleteBtn = () => {
        navigate(`/athlete`)
    }

    return (
        <div className="headerComponent">
            <header className="border-bottom border-light border-5 mb-5 p-2">
                <div className="container">
                    <div className="row">
                        <nav className="navbar navbar-expand-lg">
                            <div className="collapse navbar-collapse">
                                {isAthlete &&
                                <div>
                                    <button className="btn btn-warning btn-sm m-1" onClick = {() => handleStravaBtn()}>Strava</button>
                                    <button className="btn btn-dark btn-sm m-1" onClick = {() => handleAthleteBtn()}>Athlete</button>
                                </div>
                                }
                            </div>
                            <ul className="navbar-nav">
                                <li className="nav-item fs-5">
                                    <div>
                                        <button className="btn btn-dark btn-sm m-1">Button</button>
                                        {isAuthenticated && 
                                        <button className="btn btn-danger btn-sm m-1" onClick={() => handleLogoutBtn()}>Logout</button> }
                                    </div>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </div>
                <Toaster toastOptions={{duration: 4000}}/>
            </header>
        </div>
    )
}
export default HeaderComponent