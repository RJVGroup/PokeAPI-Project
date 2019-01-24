import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup, Dropdown, DropdownToggle, DropdownMenu, DropdownItem} from 'reactstrap';

export default class BuyMenu extends Component {
    
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.toggle = this.toggle.bind(this);
        this.state = {
            playerInventory: [],
            playerBalance: null
        }
    }


    componentDidMount() {

        fetch('api/player/show-bag',{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({playerInventory:data}))

        fetch('api/player/show-balance', {method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({playerBalance:data}))
    }
    
    handleClick = (itemIndex) => {
        fetch('api/shop/sell-item/' + itemIndex, {method: 'POST'})
        .then(response => response.json())
        .then(data => this.setState({purchaseResponse:data})
    )
    }

    toggle() {
        this.setState(prevState => ({
          dropdownOpen: !prevState.dropdownOpen
        }));
      }

    generateInventory =()=>{
         let pos = 0;
         let playerInventory=this.state.playerInventory;
        var test = []
       

        playerInventory.forEach(function(arrayItem,arrayIndex,array){
            pos=pos++;
            test.push(
                <tr>

                <td>{array[arrayIndex].itemName}</td>
                <td>{array[arrayIndex].itemPrice/2}</td>
                <td>{array[arrayIndex].itemDescription}</td>
                <td><button onClick={(e) => this.handleClick(arrayIndex, e)}>Sell</button></td>

                </tr>
                    
           )}, this)
        return test;
    }  

    generatePurchaseResponse = () => {
        if(this.state.purchaseResponse === null) {
            return null;
        }
        else if(this.state.purchaseResponse === true) {
            return <div style={{ color: 'green' }}>Succesfull Purchase</div>
            
        }
        else if(this.state.purchaseResponse === false) {
            return <div style={{ color: 'red' }}>Insufficent Funds</div>
        }
    }


    
    render() {
        return (
        <div className='col-game'>  
         

         <Container className="menu main-game-panel">
         <button  onClick={this.props.close}>Go Back</button>        
          <div>Wallet: {this.state.playerBalance}</div>

         <br/>
         <Table responsive>   
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.generateInventory()}
                        </tbody>
                    </Table>   
        </Container> 
        </div>  
        )            
        }
}
