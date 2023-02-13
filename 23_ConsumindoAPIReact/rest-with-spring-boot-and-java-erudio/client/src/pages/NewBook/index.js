import React, {useState, useEffect} from 'react';
import { useNavigate, Link, useParams } from 'react-router-dom'
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

    const {bookId} = useParams();

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');
    const navigate = useNavigate();

    async function loadBook(){
        try {
            const response = await api.get(`api/book/v1/${bookId}`, {
                headers: {
                    Authorization: `Bearer ${accessToken}` 
                }
            })

            let adjustedDate = response.data.launchDate.split("T", 10)[0];

            setId(response.data.id);
            setTitle(response.data.title);
            setAuthor(response.data.author);
            setPrice(adjustedDate);
            setLaunchDate(response.data.launchDate);

        } catch (err) {
            alert('Erro ao recuperar o livro, tente novamente');
            navigate('/books');
        }
    }

    useEffect(() => {
        if (bookId === '0') return;
        else loadBook();
    }, [bookId])
    async function saveOrUpdate(e){
        e.preventDefault();

        const data = {
            title,
            author,
            launchDate,
            price
        }
        try {
            if (bookId === '0') {
                await api.post('api/book/v1', data, {
                    headers: {
                        Authorization: `Bearer ${accessToken}` 
                    }
                });
            } else {
                data.id = id;
                await api.put('api/book/v1', data, {
                    headers: {
                        Authorization: `Bearer ${accessToken}` 
                    }
                });
            }
            
            navigate('/books');
        } catch (err) {
            alert('Erro ao gravar um novo livro, tente novamente');
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Erudio"/>
                    <h1>{bookId === '0' ? "'Add'" : "'Update'"} novo Livro</h1>
                    <p>Entre com as informações do Livro e clique em {bookId === '0' ? "'Add'" : "'Update'"}</p>
                    <Link className="back-link" to="/books">
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Back to Book
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
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
                    <button className="button" type="submit">{bookId === '0' ? 'Add' : 'Update'}</button>
                </form>
            </div>
        </div>
    );
}