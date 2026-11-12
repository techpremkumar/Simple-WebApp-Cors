import { useEffect, useState } from "react";

function App() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("http://localhost:8080/api/products")
            .then(response => response.json())
            .then(data => setProducts(data))
            .catch(error => console.error("Error:", error));
    }, []);

    return (
        <div>
            <h1>Product Store</h1>

            {products.map(product => (
                <div key={product.id}>
                    <h3>{product.name}</h3>
                    <p>{product.brand}</p>
                    <p>₹{product.price}</p>
                </div>
            ))}
        </div>
    );
}

export default App;