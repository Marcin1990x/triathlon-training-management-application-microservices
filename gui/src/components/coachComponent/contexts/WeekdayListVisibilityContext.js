import { createContext, useContext, useState } from "react";

const WeekdayListVisibilityContext = createContext()
export const useWeekdayListVisibility = () => useContext(WeekdayListVisibilityContext)


const WeekdayListVisibilityProvider = ({children}) => {
    const [isListVisible, setIsListVisible] = useState(false)

    const setVisibility = (isVisible) => {
        setIsListVisible(isVisible)
    }
    return (
        <WeekdayListVisibilityContext.Provider value = {{isListVisible, setVisibility}}>
            {children}
        </WeekdayListVisibilityContext.Provider>
    )
}
export default WeekdayListVisibilityProvider