import React from 'react';
import {Link} from 'react-router-dom';
import {FiPower, FiEdit, FiTrash2} from 'react-icons/fi';
import './styles.css';
import logoImage from '../../assets/logo.svg'

export default function Books(){
    return (
        <div className="book-container">
            <header>
                <img src={logoImage} alt="Erudio"/>
                <span>Welcome, <strong>Leandro</strong></span>
                <Link className="button" to="book/new">Add new Book</Link>
                <button type="button">
                    <FiPower size={18} color="#251FC5"/>
                </button>
            </header>

            <h1>Livros Registrados</h1>
            <ul>
                <li>
                    <strong>Titulo:</strong>
                    <p>Docker Deep Dive</p>
                    <strong>Autor:</strong>
                    <p>Maxuel Tobá Junior</p>
                    <strong>Preço:</strong>
                    <p>R$ 47,90</p>
                    <strong>Data:</strong>
                    <p>12/07/2017</p>
                </li>

                <button type="button">
                    <FiEdit size={20} color="#251FC5"/>
                </button>
                <button type="button">
                    <FiTrash2 size={20} color="#251FC5"/>
                </button>
                
            </ul>
        </div>
    );
}