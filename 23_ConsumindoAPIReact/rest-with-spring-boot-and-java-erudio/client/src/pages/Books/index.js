import React, {useState, useEffect} from 'react';
import {Link, useNavigate} from 'react-router-dom';
import {FiPower, FiEdit, FiTrash2} from 'react-icons/fi';
import './styles.css';
import logoImage from '../../assets/logo.svg'
import api from '../../services/api'

export default function Books(){

    const [books, setBooks] = useState([]);
    const [page, setPage] = useState(0);

    const username = localStorage.getItem('username');
    const accessToken = localStorage.getItem('accessToken');

    const navigate = useNavigate();

    async function logout(){
        localStorage.clear();
        navigate('/');
    }

    async function editBook(id){
        try {
            navigate(`/book/new/${id}`, )
        } catch (err) {
            alert('falha ao editar livro, tente novamente');
        }
    }

    async function deleteBook(id){
        try {
        await api.delete(`api/book/v1/${id}`, {
            headers: {
                Authorization: `Bearer ${accessToken}` 
            }
        })

        setBooks(books.filter(book => book.id !== id))

        } catch (err) {
            alert('Houve uma falha ao deletar, tente novamente');
        }
    }

    async function fecthMoreBooks(){
        const response = await api.get('api/book/v1', {
            headers: {
                Authorization: `Bearer ${accessToken}` 
            },
            params: {
                page: page,
                limit: 4,
                direction: 'asc'
            }
        });

        setBooks([ ...books , ...response.data._embedded.bookVOes])
        setPage(page + 1);

    }

    useEffect(() => {
        fecthMoreBooks();
    }, []);

    return (
        <div className="book-container">
            <header>
                <img src={logoImage} alt="Erudio"/>
                <span>Welcome, <strong>{username.toUpperCase()}</strong></span>
                <Link className="button" to="/book/new/0">Add new Book</Link>
                <button onClick={logout} type="button">
                    <FiPower size={18} color="#251FC5"/>
                </button>
            </header>

            <h1>Livros Registrados</h1>
            <ul>
                {books.map(book => (
                    <li key={book.id}>
                        <strong>Titulo:</strong>
                        <p>{book.title}</p>
                        <strong>Autor:</strong>
                        <p>{book.author}</p>
                        <strong>Preço:</strong>
                        <p>{Intl.NumberFormat('pt-BR', {style: 'currency', currency: 'BRL'}).format(book.price)}</p>
                        <strong>Data:</strong>
                        <p>{Intl.DateTimeFormat('pt-BR').format(new Date(book.launchDate))}</p>

                        <button onClick={() => editBook(book.id)}type="button">
                            <FiEdit size={20} color="#251FC5"/>
                        </button>
                        <button onClick={() => deleteBook(book.id)} type="button">
                            <FiTrash2 size={20} color="#251FC5"/>
                        </button>
                    </li>
                ))}
            </ul>

            <button className="button" onClick={fecthMoreBooks} type="button">Load More</button>
        </div>
    );
}