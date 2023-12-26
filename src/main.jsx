import 'bootstrap/dist/css/bootstrap.min.css';
import React from 'react';
import ReactDOM from 'react-dom/client';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import App from './App.jsx';
import './index.css';
import ErrorPage from './pages/ErrorPage.jsx';
import MainPage from './pages/MainPage.jsx';
import AdminPage from './pages/AdminPage.jsx';
import Page1 from './pages/Page1.jsx';
import Page2 from './pages/Page2.jsx';
import Page3 from './pages/Page3.jsx';
import Page4 from './pages/Page4.jsx';
import PageEdit from './pages/PageEdit.jsx';
import SearchPage from './pages/SearchPage.jsx';

const routes = [
  {
    index: true,
    path: '/',
    element: <MainPage />,
    title: 'Главная страница',
  },
  {
    path: '/page1',
    element: <Page1 />,
    title: 'Каталог',
  },
  {
    path: '/page2',
    element: <Page2 />,
    title: 'Категории',
  },
  {
    path: '/page3',
    element: <Page3 />,
    title: 'Информация',
  },
  {
    path: '/page4',
    element: <Page4 />,
    title: 'Лучшие фильмы',
  },
  {
    path: '/adminpage',
    element: <AdminPage />,
  },
  {
    path: '/page-edit/:id?',
    element: <PageEdit />,
  },
  {
    path: '/searchPage',
    element: <SearchPage />,
    title: 'Поиск',
  },
];

const router = createBrowserRouter([
  {
    path: '/',
    element: <App routes={routes} />,
    children: routes,
    errorElement: <ErrorPage />,
  },
]);

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
);
