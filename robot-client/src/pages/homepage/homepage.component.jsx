import React from "react";
import "./homepage.styles.scss";
import CommandUi from '../../components/command-ui/command-ui.component'


const HomePage = () => {
    return (
        <div className="home-page">
            <CommandUi />
            <CommandUi />
        </div>
    );
};

export default HomePage;