import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup, Dropdown, DropdownToggle, DropdownMenu, DropdownItem} from 'reactstrap';

export default class BuyMenu extends Component {
    
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
        this.toggle = this.toggle.bind(this);
        this.state = {
            shopstock: [],
            playerbalance: null,
            itemdescription:'',
            purchaseResponse: null,
            dropdownOpen: false
        }
    }


    componentDidMount() {

        fetch('api/shop/generate',{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({shopstock:data}))

        fetch('api/player/show-balance', {method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({playerbalance:data}))
    }
    
    handleClick = (itemIndex) => {
        fetch('api/shop/buy-item/' + itemIndex, {method: 'POST'})
        .then(response => response.json())
        .then(data => this.setState({purchaseResponse:data})
    )
    }

    toggle() {
        this.setState(prevState => ({
          dropdownOpen: !prevState.dropdownOpen
        }));
      }

    generateshop =()=>{
         let pos = 0;
         let shopstock=this.state.shopstock;
        var test = []
       

        shopstock.forEach(function(arrayItem,arrayIndex,array){
            pos=pos++;
            test.push(
                <tr>

                <td>{array[arrayIndex].itemName}</td>
                <td>{array[arrayIndex].itemPrice}</td>
                <td>{array[arrayIndex].itemDescription}</td>
                <td><button onClick={(e) => this.handleClick(arrayIndex, e)}>Buy</button></td>

                </tr>
                    
           )})
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
          <div>Wallet: {this.state.playerbalance}</div>

          <div>{this.generatePurchaseResponse()}</div>
            <br/>

        <Dropdown isOpen={this.state.dropdownOpen} toggle={this.toggle}>
            <DropdownToggle caret>
            Buy Item
            </DropdownToggle>
                <DropdownMenu>
                <DropdownItem header>Item:</DropdownItem>
                <DropdownItem divider />
                <DropdownItem onClick={(e) => this.handleClick(0, e)}>1</DropdownItem>
                <DropdownItem onClick={(e) => this.handleClick(1, e)}>2</DropdownItem>
                <DropdownItem onClick={(e) => this.handleClick(2, e)}>3</DropdownItem>
                <DropdownItem onClick={(e) => this.handleClick(3, e)}>4</DropdownItem>
            </DropdownMenu>
        </Dropdown>

         <br/>
         <Table responsive>   
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.generateshop()}
                        </tbody>
                    </Table>   
        </Container> 
        </div>  
        )            
        }
}
