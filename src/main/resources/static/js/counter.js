let counter = 0;
const bindObj = {}

function increment(){
    if(counter <= 99){
        counter = counter + 1;
        bindObj.qty = counter;
    }
}

function decrement(){
    if(counter - 1 >= 0){
        counter = counter - 1;
        bindObj.qty = counter;
    }
}

window.onload = function(){
    counterRef = document.getElementById('quantity');
    
    Object.defineProperty(bindObj,"qty",{
        get(){
            return counterRef.value;
        },
        set(newValue){
            counterRef.value = newValue;
        }
    })
    bindObj.qty = 0;
};
