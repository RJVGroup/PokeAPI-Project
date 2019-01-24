import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';
import FrontImg from './FrontImg';

export default class FightMenu extends Component {
    
    constructor(props) {
        super(props);

        this.state = {

        }
    }


    componentDidMount() {
    
    }
  
    generatemoves =()=>{
        
    }  
   
    
    render() {
        return (
                <div className='col-game'>  
         

         <Container className="menu main-game-panel">
         <button  onClick={this.props.close}>Go Back</button>        
  
        </Container> 
        </div>  
        )            
        }
}

