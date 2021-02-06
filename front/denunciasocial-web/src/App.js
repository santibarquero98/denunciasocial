import logo from './logo.svg';
import './App.css';
import './components/HeaderComponentFolder/HeaderComponent'
import HeaderComponent from './components/HeaderComponentFolder/HeaderComponent';
import MainbodyComponent from './components/MainbodyComponentFolder/MainbodyComponent';

function App() {
  return (
    <div className="App">
      <header className="Header">
        <HeaderComponent/>
      </header>
    </div>
  );
}

export default App;
