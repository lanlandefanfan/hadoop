<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Active_user extends Model
{
    protected $table='active_user';
    protected $primaryKey = "id";
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = [
        'app_key', '1_4s', '5_10s', '11_30s','31_60s','1_3m', '4_10m', '11_30m', '31_60m','60_m','datetime',
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
        
    ];
    public $timestamps =true;
}
