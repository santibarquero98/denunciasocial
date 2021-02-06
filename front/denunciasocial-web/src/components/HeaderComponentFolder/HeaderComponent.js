import React, { Component } from 'react';
import './css/HeaderComponent.css';
class HeaderComponent extends React.Component {

    render() {
        return (
            <nav>
                <ul>
                    <li><a href="#">Inicio</a></li>
                    <li><a href="#">PlusValue</a></li>
                    <li><a href="#">Denuncias</a></li>
                    <li><a href="#">Acerca de</a></li>
                    <li><a href="#">Contacto</a></li>
                </ul>
            </nav>
        );
    }

}

export default HeaderComponent;