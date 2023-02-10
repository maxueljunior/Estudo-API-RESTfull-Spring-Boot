import React, {useState} from 'react';
import { useHistory, Link } from 'react-router-dom'
import './styles.css';
import logoImage from '../../assets/logo.svg'
import {FiArrowLeft} from 'react-icons/fi'
import api from '../../services/api'

export default function NewBook(){

    const [id, setId] = useState(null);
    const [author, setAuthor] = useState('');
    const [launchDate, setLaunchDate] = useState('');
    const [price, setPrice] = useState('');
    const [title, setTitle] = useState('');

    const accessToken = localStorage.getItem('accessToken');

    const history = useHistory();

    async function createNewBook(e){
        e.preventDefault();

        const data = {
            title,
            author,
            launchDate,
            price
        }
        try {
            await api.post('api/book/v1', data, {
                headers: {
                    Authorization: `Bearer ${accessToken}` 
                }
            });
            history.push('/books');
        } catch (err) {
            alert('Erro ao gravar um novo livro, tente novamente');
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Erudio"/>
                    <h1>Add novo Livro</h1>
                    <p>Entre com as informações do Livro e clique em 'Add'</p>
                    <Link className="back-link" to="/books">
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Home
                    </Link>
                </section>
                <form onSubmit={createNewBook}>
                    <input
                        placeholder='Titulo'
                        value={title}
                        onChange={e => setTitle(e.target.value)}
                    />
                    <input
                        placeholder='Autor'
                        value={author}
                        onChange={e => setAuthor(e.target.value)}
                    />
                    <input
                        type="date"
                        value={launchDate}
                        onChange={e => setLaunchDate(e.target.value)}
                    />
                    <input
                        placeholder='Price'
                        value={price}
                        onChange={e => setPrice(e.target.value)}
                    />
                    <button className="button" type="submit">Add</button>
                </form>
            </div>
        </div>
    );
}